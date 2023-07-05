# linux 笔记

-------------

## 常见修饰符

* -i 交互模式又称安全模式
* -f 强制模式
* -r 级联模式

-------------

## 常用命令

### 对工作目录操作

####  **ls** 

*列出目录中文件*

* ls -a 列出目录所有文件，包含以.开始的隐藏文件
* ls -A 列出除.及..的其它文件
* ls -r 反序排列
* ls -t 以文件修改时间排序
* ls -S 以文件大小排序
* ls -h 以易读大小显示
* ls -l 文件详细信息
* 特殊
   * ls t*	列出当前目录中所有以"t"开头的目录的详细内容
   * ls | sed "s:^:`pwd`/:"列出文件绝对路径（不包含隐藏文件）

#### **cd** 

*转移工作目录*

* cd / 	进入根目录
* cd ~	进入 "home" 目录
* cd -	进入上一次工作路径
* cd !$	把上个命令的参数作为cd参数使用。

#### **pwd** 

*显示当前工作目录*

-----------------

### 对文件或文件夹操作

#### **mkdir**
* mkdir temp 	当前目录下创建temp目录
* mkdir -p /temp 	强制创建/temp目录

#### **rm**  

*即remove 文件或文件夹*

* rm *.c 删除任何.c文件

#### **rmdir** 
* 删除某目录时也必须具有对其父目录的写权限
* 注意：不能删除非空目录

#### **mv** 

*移动文件或修改文件名*

* ***mv test.log test1.txt***
将文件 test.log 重命名为 test1.txt
* ***mv f1.txt f2.txt f3.txt /test3***
将文件 f1.txt,f2.txt,f3.txt 移动/test3 目录中

#### **cp** 

*copy*

* ***cp -a file.c file_1.c***
复制的文件与原文件时间一样
* ***cp -s file.c link_a.c***
为 file.c 建立一个软链接（快捷方式）

#### **ln** 

*link 链接*

1. -b 删除，覆盖以前建立的链接
2. -s 软链接（符号链接）
3. -v 显示详细处理过程

--------------------

### 权限控制

#### **chmod** 

***chmod <模式> file 修改file的权限***

* 模式 *权限范围 -或+ 权限种类*
   * 权限范围
      1. u 目录或者文件的当前的用户
      2. g 目录或者文件的当前的群组
      3. o 除了目录或者文件的当前用户或群组之外的用户或者群组
      4. a 所有的用户及群组
   * 权限种类
      1. r 读权限，用数字4表示
      2. w 写权限，用数字2表示
      3. x 执行权限，用数字1表示
      4. **-** 删除权限，用数字0表示
      5. s 特殊权限

#### **chown** 

#### *change owner*

1. -c 显示更改的部分的信息
2. -R 处理指定目录及子目录

----------------

### *查找文件*

#### **which** 

*查看可执行文件的位置*

* 通过 PATH 环境变量查找

#### **whereis** 

*查看文件的位置*

1. -b 只找二进制文件
2. -m 只找在说明文件manual路径下的文件
3. -s 只找source源文件
4. -u 没有说明文档的文件 

#### **locate** 

*配合数据库查看文件位置*

#### **find** 

*实际搜寻硬盘查询文件名称*

* ***find 路径 参数***
* 按名称参数查找
   1. -name 按照文件名查找文件
   2. -perm 按文件权限查找文件
   3. -user 按文件属主查找文件
   4. -group 按照文件所属的组来查找文件
   5. -uid 按照拥有着ID来查找文件
   6. -type  查找某一类型的文件
      1. b 块设备文件
      2. d 目录
      3. c 字符设备文件
      4. l 符号链接文件
      5. p 管道文件
      6. f 普通文件
* 按时间参数查找
1. *-size n :[c]* 查找文件长度为n块文件，带有c时表文件字节大小
2. *-amin n* 查找系统中最后N分钟访问的文件
3. *-atime n* 查找系统中最后n*24小时访问的文件
4. *-cmin n* 查找系统中最后N分钟被改变文件状态的文件
5. *-ctime n* 查找系统中最后n*24小时被改变文件状态的文件
6. *-mmin n* 查找系统中最后N分钟被改变文件数据的文件
7. *-mtime n* 查找系统中最后n*24小时被改变文件数据的文件
8. *用减号-来限定更改时间在距今n日以内的文件，而用加号+来限定更改时间在距今n日以前的文件*

----------------

### 对文件内容操作

#### **cat** 

*concatenate and print files*

* ***cat file*** 	查看文件内容
* ***cat file1 file2 > file*** 合并文件
   * -n 标记行号输出
   * -b 对非空的行标记行号输出

#### **more** 

*功能与cat相似,more会以一页一页的显示方便使用者逐页阅读*   

* 命令修饰符
  1. +n 从笫 n 行开始显示
  2. -n 定义屏幕大小为 n 行
  3. +/pattern 在每个档案显示前搜寻该字串（pattern），然后从该字串前两行之后开始显示 
  4. -c 从顶部清屏，然后显示
  5. -d 提示“Press space to continue，’q’ to quit（按空格键继续，按q键退出）”，禁用响铃功能
  6. -l 忽略Ctrl+l（换页）字符
  7. -p 通过清除窗口而不是滚屏来对文件进行换页，与-c选项相似
  8. -s 把连续的多个空行显示为一行
  9. -u 把文件内容中的下画线去掉
* 常用操作命令
  1. Enter 向下 n 行，需要定义。默认为 1 行
  2. Ctrl+F 向下滚动一屏
  3. 空格键 向下滚动一屏
  4. Ctrl+B 返回上一屏
  5. = 输出当前行的行号
  6. :f 输出文件名和当前行的行号
  7. V 调用vi编辑器
  8. !(command) 调用Shell，并执行命令
  9. q 退出more

#### **less**

 *less与more类似，但使用less可以随意浏览文件，而more仅能向前移动，却不能向后移动*

#### **head** 

*与cat相似*

* ***-n number*** 以行为单位显示（number为复数表示从最后向前显示）
* ***-c char*** 以字节为单位显示

#### **tail** 

*与cat相似，逆序输出*

#### **grep** 

***Global Regular Expression Print 全局正则表达式搜索***

* ***grep [option] pattern file|dir***
   1. **-A n** *--after-context* 显示匹配字符后n行
   2. **-B n** *--before-context* 显示匹配字符前n行
   3. **-C n** *--context* 显示匹配字符前后n行
   4. **-c** *--count* 计算符合样式的列数
   5. -i 忽略大小写
   6. -l 只列出文件内容符合指定的样式的文件名称
   7. -f 从文件中读取关键词
   8. -n 显示匹配内容的所在文件中行数
   9. -R 递归查找文件夹

#### **wc** 

*word count*

* ***wc [option] file***
1. -c 统计字节数
2. -l 统计行数
3. -m 统计字符数
4. -w 统计词数 一个字被定义为由空白、跳格或换行字符分隔的字符串

--------------

### 压缩文件

#### **tar** 
1. -c 建立新的压缩文件压缩
2. -f 指定压缩文件
3. -r 添加文件到已经压缩文件包中
4. -u 添加改了和现有的文件到压缩包中
5. -x 从压缩包中抽取文件解压
6. -t 显示压缩文件中的内容
7. -z 支持gzip压缩
8. -j 支持bzip2压缩
9. -Z 支持compress解压文件
10. -v 显示操作过程
* **实际案例**
  1. *tar -cvf log.tar 1.log,2.log* 将文件全部打包成tar包
  2. *tar -zcvf file.tar.gz files* 将文件全部通过gzip压缩
  3. *tar -zxvf file.tar.gz* 将file.tar.gz 通过gzip解压


----------------

### 磁盘管理

#### **df** 

*显示磁盘空间使用情况 Disk free*

#### **du** 

*与df相似 disk usage*

1. -a 显示目录中所有文件大小
2. -k 以KB为单位显示文件大小
3. -m 以MB为单位显示文件大小
4. -g 以GB为单位显示文件大小
5. -h 以易读方式显示文件大小
6. -s 仅显示总计
7. -c或--total 显示所有目录或文件的总和

-------------

###  进程管理

#### **ps** 

***process status 用来查看当前运行的进程状态***

* 命令参数
   1. -A或-e 显示所有进程
   2. -a 显示同一终端下所有进程
   3. -w 显示加宽可以显示较多的资讯
   4. -f 以全格式显示进程
   5. -r 显示当前终端运行的进程
   6. -u 以面向用户的格式显示进程
      * %CUP 进程占用CPU的时间与进程运行的时间之比
      * %MEM 进程占用内存与总内存之比
      * VSZ 进程占用虚拟内存大小 KB为单位
      * RSS 进程占用实际内存大小 KB为单位
      * STAT 进程当前状态
         1. R 执行态
         2. S 休眠态
         3. D 不可中断休眠态
         4. T 暂停态
         5. Z 僵死态
         6. s 父进程
         7. l 多线程或克隆线程
         8. + 位于后台的线程组
* 常见格式
   1. PID 进程标识号
   2. UID 进程所属的用户名
   3. PPID 父进程标识号
   4. TTY 进程占用的终端
   5. TIME 累积使用的CPU时间
   6. STIME 进程开始时间
   7. CMD 进程的执行命令
   8. C 进程最近使用的CPU时间

#### top 

*实时显示当前系统正在执行的进程的相关信息*

1. -c 显示完整的进程命令
2. -s 保密模式
3. **-p 进程号** 指定进程显示
4. **-n 次数** 循环显示次数


#### [Command]
* ***Command&*** 后台运行命令 
* 转到前台运行
  * **fg n** *n 为进程序号*
* 转到后台运行
  * **bg n**
  * **Ctrl + Z**
* 结束进程
  * **Ctrl + C**
  * **kill -信号 PID** 

-----------------

### 时间命令

#### **date** 

*显示或设定系统的日期与时间*

1. **-d 字符串** *显示字符串所指的日期与时间*
2. **-s 字符串**　*根据字符串来设置日期与时间*
3. -u 　显示GMT
4. 用列
   * ***date +%Y%m%d --date="+1 day"*** 显示下一天的日期

#### **cal** 

*calender 日历*



## 文件目录结构

![image-20230507105250331](C:\Users\lisent124\Desktop\code\notes\markdown\image\系统目录结构.png)



----------------------



### 系统启动必须

#### boot

这里存放的是启动 Linux 时使用的一些核心文件，包括一些连接文件以及镜像文件。

#### etc

etc 是 Etcetera(等等) 的缩写,这个目录用来存放所有的系统管理所需要的**配置文件和子目录**。

#### lib

lib 是 Library(库) 的缩写这个目录里存放着系统**最基本的动态连接共享库**，其作用类似于 Windows 里的 DLL 文件。几乎所有的应用程序都需要用到这些共享库。

#### sys

该文件系统是内核设备树的一个直观反映。

---------------

### 指令集合


#### bin

bin 是 Binaries (二进制文件) 的缩写, 这个目录存放着最经常使用的命令

#### sbin

s 就是 Super User 的意思，是 Superuser Binaries (超级用户的二进制文件) 的缩写，这里存放的是系统管理员使用的系统管理程序

---------------

### 外部文件管理

#### dev

dev 是 Device(设备) 的缩写, 该目录下存放的是 Linux 的外部设备，在 Linux 中访问设备的方式和访问文件的方式是相同的。

#### media

linux 系统会自动识别一些设备，例如U盘、光驱等等，当识别后，Linux 会把识别的设备挂载到这个目录下

#### mnt

系统提供该目录是为了让用户临时挂载别的文件系统的，我们可以将光驱挂载在 /mnt/ 上，然后进入该目录就可以查看光驱里的内容了

--------------

### 账户

#### home

用户的主目录，在 Linux 中，每个用户都有一个自己的目录，一般该目录名是以用户的账号命名的，如上图中的 alice、bob 和 eve。



#### root

该目录为系统管理员，也称作超级权限者的用户主目录。

--------------------

### 共享资源

#### usr

 usr 是 unix shared resources(共享资源) 的缩写，这是一个非常重要的目录，**用户的很多应用程序和文件都放在这个目录下**，类似于 windows 下的 program files 目录。

##### bin

系统用户使用的应用程序

##### sbin

超级用户使用的比较高级的管理程序和系统守护程序

##### src

内核源代码默认的放置目录

----------------

### 扩展使用

#### opt

opt 是 optional(可选) 的缩写，这是给主机**额外安装软件所摆放的目录**。比如你安装一个ORACLE数据库则就可以放到这个目录下。默认是空的。

#### srv

 该目录存放一些服务启动之后需要提取的数据

----------------

### 运行时使用

#### proc

proc 是 Processes(进程) 的缩写，/proc 是一种伪文件系统（也即虚拟文件系统），存储的是当前内核运行状态的一系列特殊文件，这个目录是一个虚拟的目录，它是系统内存的映射，我们可以通过直接访问这个目录来获取系统信息。

这个目录的内容不在硬盘上而是在内存里

#### var

var 是 variable(变量) 的缩写，这个目录中存放着在不断扩充着的东西，我们习惯将那些经常被修改的目录放在这个目录下。包括**各种日志文件**

--------------------

### 临时文件

#### run

是一个临时文件系统，存储系统启动以来的信息。当系统重启时，这个目录下的文件应该被删掉或清除。如果你的系统上有 /var/run 目录，应该让它指向 run

#### tmp

这个目录是用来存放一些临时文件



#### lost+found

一般情况下为空的，系统非法关机后，这里就存放一些文件。