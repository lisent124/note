# ELASTICSEARCH

## 概述

Elaticsearch，简称为ES， ES是一个**开源的高扩展的分布式全文搜索引擎，**是整个Elastic Stack技术栈的核心。它可以近乎实时的存储、检索数据；本身扩展性很好，可以扩展到上百台服务器，处理PB级别的数据。

**官方地址**  <https://www.elastic.co/cn/> 

### 数据格式

Elasticsearch是面向文档型数据库，一条数据在这里就是一个文档。

我们将Elasticsearch里存储文档数据和关系型数据库MySQL存储数据的概念进行一个类比.

![image-20230630172221809](image\image-20230630172221809.png)

ES里的Index可以看做一个库，而Types相当于表，Documents则相当于表的行。

这里Types的概念已经被逐渐弱化，Elasticsearch 6.X中，一个index下已经只能包含一个type，**Elasticsearch 7.X中, Type的概念已经被删除了**

#### 索引 Index

一个索引就是一个拥有几分相似特征的文档的集合

#### 类型 Type

在一个索引中，你可以定义一种或多种类型。

一个类型是你的索引的一个逻辑上的分类/分区，其语义完全由你来定。通常，会为具有一组共同字段的文档定义一个类型。不同的版本，类型发生了不同的变化

| 版本 | Type                                           |
| ---- | ---------------------------------------------- |
| 5.x  | 支持多种type                                   |
| 6.x  | 只能有一种type                                 |
| 7.x  | 默认不再支持自定义索引类型（默认类型为：_doc） |

#### 文档 Document

一个文档是一个可被索引的基础信息单元，也就是一条数据。

在一个index/type里面，你可以存储任意多的文档。

#### 字段 Field

相当于是数据表的字段，对文档数据根据不同属性进行的分类标识。

#### 映射 Mapping

mapping是处理数据的方式和规则方面做一些限制，如：某个字段的数据类型、默认值、分析器、是否被索引等等。



### 其他概念

#### 分片 Shards

为解决一个索引存储超出单个节点硬件限制的大量数据，这个问题，Elasticsearch提供了将索引划分成多份的能力，每一份就称之为分片。

分片很重要，主要有两方面的原因：

1）允许你水平分割 / 扩展你的内容容量。

2）允许你在分片之上进行分布式的、并行的操作，进而提高性能/吞吐量。

至于一个分片怎样分布，它的文档怎样聚合和搜索请求，是完全由Elasticsearch管理的，对于作为用户的你来说，这些都是透明的，无需过分关心。



#### 副本 Replicas

为分片创建的一份或多份拷贝，这些拷贝叫做复制分片(副本)。

复制分片之所以重要，有两个主要原因： 

1. 在分片/节点失败的情况下，提供了高可用性。因为这个原因，注意到复制分片从不与原/主要（original/primary）分片置于同一节点上是非常重要的。
2.  扩展你的搜索量/吞吐量，因为搜索可以在所有的副本上并行运行。

#### 集群 Cluster

一个运行中的 Elasticsearch 实例称为一个节点，而集群是由一个或者多个拥有相同 cluster.name 配置的节点组成， 它们共同承担数据和负载的压力。当有节点加入集群中或者从集群中移除节点时，集群将会重新平均分布所有的数据。

#### 节点 Node

一个节点通常为一个服务器，其中可以负载多个分片。

当节点因为某些原因开启或关闭时，系统会自动调节集群中节点的关系已经负载的分片。



## 分片相关

### 路由计算

当一个索引有多个分片时，文档应该存放在那个分片中呢？

shard = hash(routing) % number_of_primary_shards

routing 是一个可变值，默认是文档的 _id 。

routing 通过 hash 函数生成一个数字，然后这个数字再除以 number_of_primary_shards （主分片的数量）后得到余数 。这个分布在 0 到 number_of_primary_shards-1 之间的余数，就是我们所寻求的文档所在分片的位置。

### 分片控制

#### 写操作

写操作一般包括，新建，更新，删除文档

1. 客户端向主节点发送写操作请求
2. 主节点通过**路由计算**确定写操作对象所属分片，并将请求转发到该分片所负载的节点上。
3. 执行写操作，同时将请求并行转发到副本分片所属节点上。
4. 一旦副本节点向原节点都报告成功，原节点会向协调节点报告成功，协调节点向客户端报告成功。



有一些可选的请求参数允许您影响这个过程，可能以数据安全为代价提升性能。

- consistency

one，all， 默认为quorum=**int( (primary + number_of_replicas) / 2 ) + 1**

  主分片有规定数量的分片副本处于可用状态才会去执行_写_操作

- timeout

当没有足够分片数量时等待满足条件的最大时间

#### 读操作

我们可以从主分片或者从其它任意副本分片检索文档，即读操作

1. 客户端向主节点发送请求
2. 主节点通过**路由计算**确定写操作对象所属分片，并将请求转发到该分片或其副本分片所负载的节点上。
3. 该节点处理请求，并将文档返回客户端

路由计算后可能会得到多个可转发的节点对象，此时会通过轮询所有的副本分片来达到负载均衡。

在文档被检索时，已经被索引的文档可能已经存在于主分片上但是还没有复制到副本分片。 在这种情况下，副本分片可能会报告文档不存在，但是主分片可能成功返回文档。 一旦索引请求成功返回给用户，文档在主分片和副本分片都是可用的。

#### 多文档操作

Bulk，mget 操作



1. 客户端向主节点发送请求
2. 主节点并行转发这些请求到对于分片或者副本所在节点上
3. 其他节点返回应答给协调节点，一旦收到所有响应，协调节点构建响应返回给客户端

### 分片原理

#### 倒排索引

把文件ID对应到关键词的映射转换为关键词到文件ID的映射，每个关键词都对应着一系列的文件，这些文件中都出现这个关键词。

#### 索引不变性

一旦创建了一个索引并存储了文档，就不能对该索引的核心结构进行修改。这意味着你不能更改索引的字段映射、属性设置或索引级别的配置，也不能修改已经索引的文档的内容。

### 设置合理的分片数

分片和副本的设计为 ES 提供了支持分布式和故障转移的特性，但并不意味着分片和副本是可以无限分配的。而且索引的分片完成分配后由于索引的路由机制，我们是不能重新修改分片数的。

多个分片会提高效率但也会消耗更多的资源

一般来说确定合理的分片数量与下面几个方面有关

- 一个索引业务将来的发展

控制每个分片占用的硬盘容量不超过ES的最大JVM的堆空间设置，一般来说一个分片最大设置32G，可根据索引的大小确定分片数量

- 节点数量

一般一个节点有时候就是一台物理机，如果分片数过多，大大超过了节点数，很可能会导致一个节点上存在多个分片，一旦该节点故障，即使保持了1个以上的副本，同样有可能会导致数据丢失，集群无法恢复。所以， 一般都设置分片数不超过节点数的3倍。

- 节点，分片，副本 三者之间的关系

**节点数<=主分片数*（副本数+1）**

## 文档相关

#### 文档分析

文档分析包括两方面

-  将一块文本分成适合于倒排索引的独立的 词条

- 将这些词条统一化为标准格式以提高它们的“可搜索性”，或者 recall

分析器执行上面的工作。主要功能可分为

- 字符过滤器

首先，字符串按顺序通过每个 字符过滤器 。他们的任务是在分词前整理字符串。一个字符过滤器可以用来去掉HTML，或者将 & 转化成 and。

- 分词器

其次，字符串被 分词器 分为单个的词条。一个简单的分词器遇到空格和标点的时候，可能会将文本拆分成词条。

- Token过滤器

最后，词条按顺序通过每个 token 过滤器 。这个过程可能会改变词条（例如，小写化 ），删除词条（例如， 像 a， and， the 等无用词），或者增加词条（例如，像 jump 和 leap 这种同义词）。

#### 文档冲突处理

在数据库中可能会出现短时间内对同一条数据并行处理导致前后数据不一致的情况。

为防止该情况发生，数据库会针对的采取一些控制方式。

- 悲观并发控制

这种方法被关系型数据库广泛使用，它假定有变更冲突可能发生，因此阻塞访问资源以防止冲突。 一个典型的例子是读取一行数据之前先将其锁住，确保只有放置锁的线程能够对这行数据进行修改。

- 乐观并发控制

Elasticsearch 中使用的这种方法假定冲突是不可能发生的，并且不会阻塞正在尝试的操作。 然而，如果源数据在读写当中被修改，更新将会失败。应用程序接下来将决定该如何解决冲突。 例如，可以重试更新、使用新的数据、或者将相关情况报告给用户。

#### 乐观并发控制

在Elasticsearch中，对于正在索引的文档，它使用了一种称为版本控制的机制。每个文档都有一个唯一的版本号，当多个线程或节点同时索引同一文档时，Elasticsearch会比较版本号以确保并发的正确性。



## 操作ES

### 索引操作

#### 创建索引

```http
PUT /indexName
```

```json
{
    "acknowledged" : true, // true操作成功
    "shards_acknowledged": true, // 分片操作成功
    "index": "shopping" // 索引名称
}

```



#### 查看所有索引

```http
GET /_cat/indices?v	 
```

 

返回的信息为表格 ，其信息为如下

| 表头           | 含义                                                         |
| -------------- | ------------------------------------------------------------ |
| health         | 当前服务器健康状态：  **green**(集群完整) **yellow**(单点正常、集群不完整)  red(单点不正常) |
| status         | 索引打开、关闭状态                                           |
| index          | 索引名                                                       |
| uuid           | 索引统一编号                                                 |
| pri            | 主分片数量                                                   |
| rep            | 副本数量                                                     |
| docs.count     | 可用文档数量                                                 |
| docs.deleted   | 文档删除状态（逻辑删除）                                     |
| store.size     | 主分片和副分片整体占空间大小                                 |
| pri.store.size | 主分片占空间大小                                             |

#### 查看单个索引

```http
GET /indexName	
```



```json
{
    "indexName": {        
        "aliases": {
            ...
        }, // 别名
        "mappings": {
            ...
        }, // 映射
        "settings": {
            "index": {
                "creation_date": "1614265373911", // 索引创建时间
                "number_of_shards": "1", // 主分片数量
                "number_of_replicas": "1", // 副分片数量
                "uuid": "eI5wemRERTumxGCc1bAk2A",
                "version": {
                    "created": "7080099"
                },
                "provided_name": "indexName"
            }
        }
    }
}

```



#### 删除索引

```http
DELETE /indexName	
```

### 文档操作 

#### 创建文档

```http
POST /indexName/docType 
POST /indexName/docType/id

Body
{
	"title":value
}
```

```json
{
    "_index": "indexName",
    "_type": "docType", // 默认为 _doc
    "_id": "Xhsa2ncBlvF_7lxyCE9G", // 可以类比为MySQL中的主键，随机生成
    "_version": 1,
    "result": "created", // 这里的create表示创建成功
    "_shards": { // 分片信息
        "total": 2, // 分片数量
        "successful": 1,
        "failed": 0
    },
    "_seq_no": 0,
    "_primary_term": 1
}

```

#### 查看文档

```http
GET /indexName/docType/id
```

```json
{
    "_index": "indexName",
    "_type": "docType",
    "_id": "1",
    "_version": 2,
    "_seq_no": 2,
    "_primary_term": 2,
    "found": true, // 查询结果 
    "_source": { // 文档数据信息
        "title":value
    }
}

```



#### 修改文档

同创建文档相似

```http
POST /indexName/docType/id

Body
{
	"title": value
}
```

```http
POST /indexName/docType/id

Body
{
	"doc":{
		"title": value
	}
}
```



```json
{
    "_index": "indexName",
    "_type": docType,
    "_id": id,
    "_version": 2,
    "result": "updated", // updated表示数据被更新
    "_shards": {
        "total": 2,
        "successful": 1,
        "failed": 0
    },
    "_seq_no": 2,
    "_primary_term": 2
}

```

#### 删除文档

删除一个文档不会立即从磁盘上移除，它只是被标记成已删除（逻辑删除）

```http
DELETE /indexName/docType/id
```

```json
{
    "_index": "indexName",
    "_type": "docType",
    "_id": id,
    "_version": 2,
    "result": "delete",
    "_shards": {
        "total": 2,
        "successful": 1,
        "failed": 0
    },
    "_seq_no": 2,
    "_primary_term": 2
}
```



#### 条件删除

```http
POST /indexName/_delete_by_query

Body
{
	"querY":{
		"match":{
			"title": value1
		}
	}
}
```



```json
{
    "took": 175, // 耗时
    "timed_out": false, // 是否超时
    "total": 2,
    "deleted": 2, // 删除数量
    "batches": 1,
    "version_conflicts": 0,
    "noops": 0,
    "retries": {
        "bulk": 0,
        "search": 0
    },
    "throttled_millis": 0,
    "requests_per_second": -1.0,
    "throttled_until_millis": 0,
    "failures": []
}

```

### 映射操作

映射类似于数据库(database)中的表结构(table)

#### 创建映射

```http
PUT /indexName/_mapping

Body
{
	"properties":{
		"title":{
			"type": typeValue,
			"index": true   // false
		},
		"title1":{
			...
		}
	}
}
```



映射数据说明： 

- **title**: 字段名：任意填写，下面指定许多属性，例如：title、subtitle、images、price

- **type**：类型，Elasticsearch中支持的数据类型非常丰富，说几个关键的：

  - **String**类型，又分两种：

    - text：可分词，可以把文章内容进行分词存储在倒排索引里面，当查询的时候分词和倒排索引进行比对，比对上数据就可以查询出来。

    - keyword：不可分词，数据会作为完整字段进行匹配

  - **Numerical**：数值类型，分两类

    - 基本数据类型：long、integer、short、byte、double、float、half_float

    - 浮点数的高精度类型：scaled_float

  - **Date**：日期类型

  - **Array**：数组类型

  - **Object**：对象

- **index**：是否索引，默认为true，也就是说你不进行任何配置，所有字段都会被索引。

  - true：字段会被索引，则可以用来进行搜索

  - false：字段不会被索引，不能用来搜索

- **analyzer**：分词器，这里的ik_max_word即使用ik分词器,后面会有专门的章节学习

#### 查看映射

```http
GET /indexName/_mapping
```



```json

{
    indexName:{
        "mappings":{
            "properties":{
           		"title": {
                    "type": typeValue,
                    "index": boolean
                },
                ...
            }
        }
    }
}

```

#### 索引映射的关联

```http
PUT /indexName

Body
{
	"settings": {
		...
	},
	"mappings": {
		"properties": {
			...
		}
	}
}

```

## 高级查询

一下查询请求只有请求体有所不同

```http
GET /indexName/_search

Body{
// 有所不同
}
```



### 返回格式

查询的返回结果都是同一格式：

```json
{
    "took" : 1116,
    "timed_out" : false,
    "_shards" : {
        "total" : 1,
        "successful" : 1,
        "skipped" : 0, 
        "failed" : 0
    },
    "hits" : { // 命中结果
        "total": { // 搜索条件匹配的文档总数
            "value": 3, // 命中计数的值
            "relation": "eq" // 计数规则 eq 表示计数准确， gte表示计数不准确
        },
        "max_score" : 1.0, // 匹配度分值
        "hits" : [ // 命中结果 对象集合
            ...
        ]
    }	
}

```

### 返回显示

#### 显示字段

```json

{
  "_source":{
  	 "includes": ["title","title1"] //来指定想要显示的字段 若为excludes则为排除字段
  },  
  "query": {
    	...
  }
}
// 或者
{
	"_source": ["title","title1"] //来指定想要显示的字段
	"query": {
		...
	}
}

```

#### 显示排序

```json
{
  "query": {
  	...
  },
  "sort": [{
    "title": {
        "order":"desc" // 或者 asc
    }
  }]
}

```

#### 显示高亮

```json
{
  "query": {
      ...
  },
  "highlight": {
    "pre_tags": "<font color='red'>", //前置标签
    "post_tags": "</font>", // 后置标签
    "fields": {  // 需要高亮的字段
      "title": {} //可指定对象选择高亮
    }
  }
}

```

#### 显示分页

```json
{
  "query": {
    ...
  },
  "from": 0, // 当前页的起始索引，默认从0开始
  "size": 2 //每页显示多少条 
}

```



#### 聚合显示

聚合允许使用者对es文档进行统计分析，类似与关系型数据库中的group by，当然还有很多其他的聚合，例如取最大值、平均值等等。

```json
{
    "aggs":{
      "group_title":{ // 分组名称
        "terms":{ // 字段分组
            "field": "title"
        }
      }
    }
}

```



- terms 分组
- max 最大值
- min 最小值
- cardinality 去重
- avg 平均值
- sum 求和
- count 计数
- stats 对某个字段一次性返回count，max，min，avg和sum五个指标



### 查询请求体

#### 查询所有文档

```json
{
    "query":{
    	"match_all":{}
    }
}
```



#### Match匹配查询

##### 单字段匹配

match匹配类型查询，会把查询条件进行分词，然后进行查询，多个词条之间是or的关系

```json
{
    "query": {
        "match": {
        	"title": value // 匹配的字段
        }
    }
}
```

##### 多字段匹配

multi_match与match类似，不同的是它可以在多个字段中查询

```json
{
    "query":{
        "multi_match":{
            "query":"zhangsan",//查询数据
            "fields":["title","title1"]//查询的字段
        }
    }
}

```

#### Term精确查询

term查询，精确的关键词匹配查询，不对查询条件进行分词

```json
{
    "query": {
        "term": [
            "title": {
                "value": "zhangsan" //单值精确查询
            },
            "title1": ["value","value1"], //多值精确查询
            ...
        ]
    }
}
```

#### Bool查询

```json
{
    "query": {
        "bool": {
            "must": [
               {
                    "match": {
                   	 	"age": "40"
                    }
                }
            ],
            "must_not": [
               {...}
            ],
            "should": [
               {...}
            ]
        }
    }
}
```



#### Range范围查询

| 操作符                        | 说明       |
| ----------------------------- | ---------- |
| gt(greater than)              | 大于>      |
| gte(greater than or equal to) | 大于等于>= |
| lt(less than)                 | 小于<      |
| lte(less than or equal to)    | 小于等于<= |

```json
{
      "query": {
            "range": {
                  "title": {
                        "gte": 30,
                        "lte": 35
                  }
            }
      }
}
```



## 重要配置

| 参数名                             | 参数值        | 说明                                                         |
| ---------------------------------- | ------------- | ------------------------------------------------------------ |
| cluster.name                       | elasticsearch | 配置 ES 的集群名称，默认值是ES，建议改成与所存数据相关的名称，ES 会自动发现在同一网段下的集群名称相同的节点 |
| node.name                          | node-1        | 集群中的节点名，在同一个集群中不能重复。节点的名称一旦设置，就不能再改变了。当然，也可以设置成服务器的主机名称，例如 node.name:${HOSTNAME}。 |
| node.master                        | true          | 指定该节点是否有资格被选举成为 Master 节点，默认是  True，如果被设置为 True，则只是有资格成为  Master 节点，具体能否成为 Master 节点，需要通过选举产生。 |
| node.data                          | true          | 指定该节点是否存储索引数据，默认为 True。数据的增、删、改、查都是在 Data 节点完成的。 |
| index.number_of_shards             | 1             | 设置都索引分片个数，默认是 1 片。也可以在创建索引时设置该值，具体设置为多大都值要根据数据量的大小来定。如果数据量不大，则设置成 1 时效率最高 |
| index.number_of_replicas           | 1             | 设置默认的索引副本个数，默认为 1 个。副本数越多，集群的可用性越好，但是写索引时需要同步的数据越多。 |
| transport.tcp.compress             | true          | 设置在节点间传输数据时是否压缩，默认为 False，不压缩         |
| discovery.zen.minimum_master_nodes | 1             | 设置在选举 Master 节点时需要参与的最少的候选主节点数，默认为 1。如果使用默认值，则当网络不稳定时有可能会出现脑裂。  合理的数值为(master_eligible_nodes/2)+1，其中 master_eligible_nodes 表示集群中的候选主节点数 |
| discovery.zen.ping.timeout         | 3s            | 设置在集群中自动发现其他节点时 Ping 连接的超时时间，默认为  3 秒。  在较差的网络环境下需要设置得大一点，防止因误判该节点的存活状态而导致分片的转移 |

## 常见问题

### ES的master选举流程

1. 启动阶段

当你启动一个Elasticsearch节点时，它会检查集群中是否已存在Master节点。如果没有找到Master节点，该节点会尝试成为Master节点。

1. Master候选节点的选举

启动的节点将向集群中的其他节点发送请求，宣布自己成为Master候选节点，并请求其他节点的支持。这些请求将包含节点的唯一标识符和版本号。

1. 选举投票

集群中的每个节点都会对Master候选节点进行评估，并给出自己的投票。节点根据配置的优先级、节点的稳定性、节点的硬件资源等因素来决定投票结果。节点可以投票给多个Master候选节点，但通常只会选择一个。

1. Master节点选举结果

当一个Master候选节点收到超过一半节点的支持时，它将成为新的Master节点，并负责整个集群的管理和协调。选举结果会被广播到集群中的所有节点，以便它们可以更新自己的状态和角色。

### ES集群脑裂问题

Elasticsearch集群脑裂问题（Split Brain）是指在一个分布式集群中，由于网络分区或其他原因导致集群内的节点无法相互通信，最终导致集群分裂成多个独立的子集群。这种情况下，每个子集群都可能认为自己是合法的集群，导致数据的不一致性和操作的冲突。

#### 产生原因

- 网络问题

集群间的网络延迟导致一些节点访问不到master，认为master挂掉了从而选举出新的， master，并对master上的分片和副本标红，分配新的主分片

- 节点负载

 节点的角色既为master又为data，访问量较大时可能会导致ES停止响应造成大面积延迟，此时其他节点得不到主节点的响应认为主节点挂掉了，会重新选取主节点。

- 内存回收

data节点上的ES进程占用的内存较大，引发JVM的大规模内存回收，造成ES进程失去响应。

#### 预防方法

- 减少误判

通过延长心跳检测时长，减少误判

- 选举触发

通过提高选举条件，来减少集群的分裂

- 角色分离

master节点与data节点分离，限制角色



### ES索引文档的流程

1.  协调节点默认使用文档ID参与计算（也支持通过routing），以便为路由提供合适的分片：

**shard = hash(document_id) % (num_of_primary_shards)**

2. 当分片所在的节点接收到来自协调节点的请求后，会将请求写入到Memory Buffer，然后定时（默认是每隔1秒）写入到Filesystem Cache，这个从Memory Buffer到Filesystem Cache的过程就叫做refresh；

3. 当然在某些情况下，存在Momery Buffer和Filesystem Cache的数据可能会丢失，ES是通过translog的机制来保证数据的可靠性的。其实现机制是接收到请求后，同时也会写入到translog中，当Filesystem cache中的数据写入到磁盘中时，才会清除掉，这个过程叫做flush；

4. 在flush过程中，内存中的缓冲将被清除，内容被写入一个新段，段的fsync将创建一个新的提交点，并将内容刷新到磁盘，旧的translog将被删除并开始一个新的translog。
5. flush触发的时机是定时触发（默认30分钟）或者translog变得太大（默认为512M）时；



### ES如果保证读写一致

1. 可以通过版本号使用乐观并发控制，以确保新版本不会被旧版本覆盖，由应用层来处理具体的冲突；

2. 另外对于写操作，一致性级别支持quorum/one/all，默认为quorum，即只有当大多数分片可用时才允许写操作。但即使大多数可用，也可能存在因为网络等原因导致写入副本失败，这样该副本被认为故障，分片将会在一个不同的节点上重建。

3. 对于读操作，可以设置replication为sync(默认)，这使得操作在主分片和副本分片都完成后才会返回；如果设置replication为async时，也可以通过设置搜索请求参数_preference为primary来查询主分片，确保文档是最新版本。



### ES更新和删除文档的流程？

1.  删除和更新也都是写操作，但是Elasticsearch中的文档是不可变的，因此不能被删除或者改动以展示其变更；
2.  磁盘上的每个段都有一个相应的.del文件。当删除请求发送后，文档并没有真的被删除，而是在.del文件中被标记为删除。该文档依然能匹配查询，但是会在结果中被过滤掉。当段合并时，在.del文件中被标记为删除的文档将不会被写入新段。
3. 在新的文档被创建时，Elasticsearch会为该文档指定一个版本号，当执行更新时，旧版本的文档在.del文件中被标记为删除，新版本的文档被索引到一个新段。旧版本的文档依然能匹配查询，但是会在结果中被过滤掉。



### Elasticsearch中的倒排索引是什么？

倒排索引是搜索引擎的核心。

搜索引擎的主要目标是在查找发生搜索条件的文档时提供快速搜索。

ES中的倒排索引其实就是lucene的倒排索引，区别于传统的正向索引，倒排索引会再存储数据时将关键词和数据进行关联，保存到倒排表中，然后查询时，将查询内容进行分词后在倒排表中进行查询，最后匹配数据即可。

