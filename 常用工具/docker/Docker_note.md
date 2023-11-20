# Docker

## 简介

Docker 是⼀个开源的应⽤容器引擎，基于 Go 语⾔， 并遵从 Apache2.0 协议开源。 Docker 可以让开发者打包他们的应⽤以及依赖包到⼀个轻量级、可移植的容器中，然后发布到任何流⾏ 的 Linux 机器上，也可以实现虚拟化。

### docker的优点

1. 更高效的利用系统资源
2. 更快速的启动事件
3. 一致的运行环境
4. 持续的交互与部署
5. 更轻松的迁移
6. 更轻松地维护和拓展

### docker的架构

![imge-20230506171553304](image\docker架构.png)

#### docker daemon (Docker守护进程)

Docker daemon 是一个运行在宿主主机上的后台进程，可通过Docker客户端与其通信

#### Client(Docker客户端)

Docker客户端是Docker的用户界面，它可以接受用户命令和配置标识，并与Docker daemon通 信

####  Images(Docker镜像)

Docker镜像是一个只读模板，它包含创建Docker容器的说明，同系统安装光盘相似

####  Container(容器)

容器时镜像的可运行实例。它可以被启动，开始、停止、删除、 每个容器都是互相隔离的，保证安全的平台，可以把容器看做是要给简易版的linux环境（包括root 用户权限、镜像空间、用户空间和网络空间等）和运行再其中的应用程序

#### Registry(仓库)

Docker Registry是一个集中存储与分发镜像的服务。

-------------------

## 常见命令

### 镜像IMAGE

- 查看本地镜像：docker images 
- 搜索镜像：docker search **IMAGE** 
- 搜索镜像并过滤是官方的： docker search --filter "is-official=true" ***IMAGE***
- 搜索镜像并过滤大于多少颗星星的：docker search --filter stars=10 ***IMAGE***
- 下载镜像：docker pull ***IMAGE:tag*** 
- 修改本地镜像名字：docker tag ***old_image[:tag] new_image[:tag]*** 
- 本地镜像的删除：docker rmi ***IMAGE***
- 备份镜像 docker save -o ***url IMAGE***

- 备份还原 docker load -i ***url***

### 容器Container

#### 构建容器

docker run -id[t] --name=**Container** -p **POST0:POST1** **IMAGE** **Command**

- **-i** 表示以交互模式运行容器（让容器的标准输入保持打开）***interactive***
- -d 表示后台运行容器，并返回容器ID  ***detach***
- **-t** 进行交互 运行一个新的终端 /bin/bash  ***pseudo-TTY***
- -p POST0:POST1 端口映射 将容器中POST1端口映射到宿主系统的POST0端口中 
- --name 为容器指定名称
- command 替代**CMD**的命令，可作为自定义命令的参数传递

#### 查看容器信息

- 查看本地所有的容器：docker ps -a 
- 查看本地正在运行的容器：docker ps
- 查看容器详细信息：docker inspect **Container**
- 查看日志信息 docker logs **Conatiner**



#### 容器的运行

- 停止容器：docker stop **Container** 
- 一次性停止所有容器：docker stop $(docker ps -a -q) 
- 启动容器：docker start **Container** 
- 重启容器：docker restart **Container** 
- 删除容器：docker rm  [-f]  **Container**

- 进入容器，并开另一个进程执行命令：docker exec -it **Container** ***Command***

- 直接进入容器：docker attach **Container**

#### 容器文件的复制与挂载

- 复制 docker cp **url0 Container:url1**  *方向可以反转*
- 挂载 docker run -id [-privileged=true]  **-v url0:url1[:rw|ro] Container**
  - -v ***volumes*** *数据卷（Data Volumes）是宿主机中的一个目录或文件，数据卷的设计目的就是数据的持久化， 完全独立于容器的生存周期，因此Docker不会在容器删除时删除其挂载的数据卷。*
  - url0 表示宿主机绝对路径  url1表示容器路径
  - :rw **read + write (默认读写)** 
  - :ro **read only**
  - -privileged=true  container内的root拥有真正的root权限

## docker可视化

1. docker pull portainer/portainer ***拉取portainer镜像***
2. 创建容器
3. 在浏览器中打开 ip:port



## 镜像和仓库

### 镜像分层

docker的镜像实际上由一层一层的文件系统组成，这种层级的文件系统称作**UnionFS**

- **bootfs** 作为第一层负责根加载与内核加载
  - **rootfs** 一般包括  /dev, /proc, /bin, /etc 等标准目录和文 件。rootfs就是各种不同的操作系统发行版
    - **IMAGE** 在以上基础上在加入其他程序或功能，然后commit产生
    - 新的层 以后**以此重复**

### 提交镜像

docker commit -m="**MESSAGES**" -a="**AUTH**" **Container** **IMAGE[:tag]**

- -m 提交的描述信息
- -a 作者
- IMAGE[:tag]  要创建的目标镜像仓库名:[标签名]

### 仓库Repository

#### Docker Hub

Docker 官方维护了一个公共仓库 Docker Hub，其中已经包括了超过 15,000的镜像

#### 镜像发布到仓库

![image-20230506203825728](image\镜像上传到仓库.png)

##### 阿里云开发者平台

<https://cr.console.aliyun.com/cn-hangzhou/instances>

##### 脚本实例

> $ docker login --username=2728*****@qq.com registry.cn-hangzhou.aliyuncs.com 
>
> $ docker tag [ImageId] registry.cn-hangzhou.aliyuncs.com/dsy-docker/mycentos:[镜像版本号] 
>
> $ docker push registry.cn-hangzhou.aliyuncs.com/dsy-docker/mycentos:[镜像版本号]
>

#### 搭建私有库

1. 下载镜像 docker pull registry
2. 运行容器 *相当于本地有个私有Docker hub*
   - 默认情况，仓库被创建在容器的/var/lib/registry目录下，建议自行用容器卷映射，方便于宿主机联调



## Docekerfile 构建镜像

自定义镜像有两种方式，一是docker commit 制作 二是通过dockerfile制作。而后者为主流。

### 概念

- Dockerfile是用来构建Docker镜像的文本文件，是由一条条构建镜像所需的指令和参数构成的脚本。 
- 官网 https://docs.docker.com/engine/reference/builder/

### 步骤

1. 编写Dockerfile文件，需要检查是否需要对应的安装包，以及保证安装包路径正确 
2. docker build命令构建镜像 
3. docker run依镜像运行容器实例

### 常用指令

#### FROM 

基础镜像，当前新镜像是基于哪个镜像的，指定一个已经存在的镜像作为模板，第一条必须是from



#### RUN

容器构建时需要运行的命令 系统命令

- shell格式 RUN **Command**
  - 实际是调用 **sh -c ** 来执行Command

- exec格式 RUN **["可执行文件", "参数1", "参数2"]**
  - 直接执行Command


***RUN 在docker build 时运行***

#### EXPOSE

当前容器对外暴露出的端口

#### WORKDIR

指定在创建容器后，终端默认登陆的进来工作目录，一个落脚点

#### ENV

用来在构建镜像过程中设置环境变量

格式: ENV 键 值

*这个环境变量可以在后续的任何RUN指令中使用，这就如同在命令前面指定了环境变量前缀一样*

#### ADD

将宿主机目录下的文件拷贝进镜像且会自动处理URL和**自动解压tar压缩包**

#### COPY

类似ADD，拷贝文件和目录到镜像中。不会自动解压tar压缩包

- COPY src dest
- COPY ["src", "dest"]
- dest 容器内的指定路径，该路径不用事先建好，路径不存在的话，会自动创建。

#### CMD

指定容器启动后的要干的事情

**Dockerfile 中可以有多个 CMD 指令，但只有最后一个生效，CMD 会被 docker run 最后的命令参数替换**

#### ENTRYPOINT

类似于 CMD 指令，但是ENTRYPOINT**不会被docker run后面的命令覆盖**，而且这些命令行参数会被当作参数送给  ENTRYPOINT 指令指定的程序

ENTRYPOINT可以和CMD一起用，一般是变参才会使用 CMD ，这里的 CMD 等于是在给 ENTRYPOINT 传参

#### 其他

##### MAINTAINER

镜像维护者的姓名和邮箱地址

##### USER

指定该镜像以什么样的用户去执行，如果都不指定，默认是root

## 网络模式

### 命令

- docker network [command]
  - **create** ***NAME***   创建网络
  - **ls** 查看建立的网络列表
  - **inspect** ***NAME*** 查看详细信息
  - **rm** ***NAME*** 删除网络
  - **connect** [OPTIONS] **NETWORK CONTAINER** 

### 模式

#### BRIDGE模式

默认为该模式，通过 -p 指定端口映射。为每个容器分配， 设置IP，并将容器连接到 docker0

#### HOST模式

容器不会虚拟出自己的网卡，也就不会有属于自己的IP地 址，容器和宿主机共享 Network namespace，容器共享主 机的IP和端口。

#### NONE模式

容器有独立的Network namespace，但并没有对其进行任 何网络设置，如分配 veth pair 和网桥连接，配置IP等。

#### CONTAINNER模式

容器和另外一个容器共享 Network namespace。 新创建的 容器不会创建自己的网卡和配置自己的IP，而是和指定的容 器共享IP和网络桥接，端口等

## Compose

### 概念

Docker-Compose项目是Docker官方的开源项目，负责实现对Docker容器集群的快速编排。 Docker-Compose将所管理的容器分为三层，分别是工程（project），服务（service）以及容器（container）。

Docker-Compose运行目录下的所有文件（docker-compose.yml，extends文件或环境变量文件等）组成一个工程，若 无特殊指定工程名即为当前目录名。一个工程当中可包含多个服务，每个服务中定义了容器运行的镜像，参数，依赖。一 个服务当中可包括多个容器实例，Docker-Compose并没有解决负载均衡的问题，因此需要借助其它工具实现服务发现 及负载均衡。

### 安装

windows下默认和docker绑定但在linux环境下需要自行下载

`apt install docker-compose`

### 常用命令

> `docker-compose -h              # 查看帮助 `
>
> `docker-compose up              # 创建并运行所有容器 `
>
> `docker-compose up -d             # 创建并后台运行所有容器 `
>
> `docker-compose -f docker-compose.yml up -d  # 指定模板 `
>
> `docker-compose down             # 停止并删除容器、网络、卷、镜像。`
>
> ` docker-compose logs    # 查看容器输出日志` 
>
> ` docker-compose pull    # 拉取依赖镜像` 
>
> ` dokcer-compose config   # 检查配置 ` 
>
> `dokcer-compose config -q  # 检查配置，有问题才有输出 `
>
> `docker-compose restart  # 重启服务 `
>
> `docker-compose start   # 启动服务 `
>
> `docker-compose stop    # 停止服务`



### 配置文件

docker-compose.yml

常用配置

>  image 镜像名称；
>
>  build 根据docker file 打包 成镜像；
>
>  context  指定docker file文件位置； 
>
> commond 使用command可以覆盖容器启动后默认执行的命令；
>
>  container_name 容器名称；
>
>  depends_on 指定依赖那个服务； 
>
> ports 映射的端口号；
>
>  extra_hosts 会在/etc/hosts文件中添加一些记录；
>
>  volumes 持久化目录；
>
>  volumes_from 从另外一个容器挂在数据卷；

以下是一个简单的例子

```yml
networks:
  dockernet:
    driver: bridge

services:
  mysql01:
    container_name: mysql01
    restart: always
    image: mysql:latest
    networks:
      - dockernet
    ports:
      - 3307:3306
    volumes:
      - D:\docker\mysql\mysql01\log:/var/log/mysql
      - D:\docker\mysql\mysql01\conf:/etc/mysql/conf.d
      - D:\docker\mysql\mysql01\data:/var/lib/mysql
    environment:
      MYSQL_ROOT_PASSWORD: lisent
  redis01:
    container_name: redis01
    restart: always
    image: redis:latest
    ports:
      - 6380:6379
    networks:
      - dockernet
  java01:
    container_name: java01
    restart: always
    image: java:latest
    networks:
      - dockernet
    ports:
      - 8081:8080
    links:
      - mysql01:mysql01Host
      - redis01:redis01Host
    environment:
      MYSQL_HOST: mysql01Host
      REDIS_HOST: redis01Host
    volumes:
      - D:\Projects\javaProjects\docker_Frist_work\target\docker_Frist_work-1.0-SNAPSHOT.jar:/home/docker_Frist_work.jar
    entrypoint: java -jar /home/docker_Frist_work.jar
    depends_on:
      - mysql01
      - redis01

```



