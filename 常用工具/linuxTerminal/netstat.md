# NETSTAT

## 简介 

Netstat是一个用于显示网络连接和网络统计信息的命令行工具。它通常在各种操作系统上可用，包括Windows、Linux和macOS。Netstat的名称来源于"network statistics"（网络统计），它允许用户查看与网络相关的各种信息，包括正在建立的连接、已建立的连接、监听端口以及网络接口的统计信息。

## netstat

示例输出

```css
Proto  Local Address          Foreign Address        State
tcp    192.168.1.2:80        203.0.113.1:34567      ESTABLISHED
tcp    192.168.1.2:22        10.0.0.1:12345         TIME_WAIT
udp    0.0.0.0:53            0.0.0.0:*              LISTEN
```


Netstat以一种表格形式展示网络连接和统计信息。以下是netstat输出的基本格式，包括各列的含义：

1. Proto（协议）：显示连接所使用的协议，通常是TCP（传输控制协议）或UDP（用户数据报协议）。
2. Local Address（本地地址）：显示本地计算机的IP地址和端口号。格式通常为IP地址:端口号。
3. Foreign Address（远程地址）：显示远程计算机的IP地址和端口号，表示与本地计算机建立连接的远程端点。
4. State（状态）：对于TCP连接，显示连接的状态，例如ESTABLISHED（已建立）、LISTEN（监听）、TIME_WAIT（等待时间结束）等。
5. PID（进程标识符）：显示与每个网络连接关联的进程的标识符。这个信息在某些操作系统上可能需要以管理员权限运行netstat才能看到。

**可带参数**

- -e：--extend，显示额外信息
- -p：--programs，与链接相关程序名和进程的PID
- -n：-numeric，去除名字，显示数字
- -t：所有的 tcp 协议的端口
- -x：所有的 unix 协议的端口
- -u：所有的 udp 协议的端口
- -o：--timers，显示计时器
- -c：--continuous，每隔一个固定时间，执行netstat命令
- -l：--listening，显示所有监听的端口
- -a：--all，显示所有链接和监听端口



**常见命令**

- netstat -tunpl 显示TCP和UDP类型的端口
- netstat -anp：显示系统端口使用情况

## netstat -r

`netstat -r` 是用于查看路由表的netstat命令选项。路由表是一个记录网络数据包如何在计算机网络中传输的表格。它包含了路由信息，决定了数据包应该通过哪个网络接口发送以及它们的下一跳（next hop）目的地。以下是`netstat -r` 命令输出的一些示例：

```
Destination        Gateway            Genmask           Flags Metric Ref    Use Iface
default            router             0.0.0.0           UG    100    0        0 eth0
192.168.1.0        0.0.0.0            255.255.255.0     U     0      0        0 wlan0
10.0.0.0           0.0.0.0            255.0.0.0         U     0      0        0 eth1
```

在这个示例中，每一行表示一条路由记录，包含以下列：

- Destination：目的网络或目标IP地址。这表示数据包的目的地，可以是一个具体的IP地址或一个网络地址。
- Gateway：下一跳网关。这是数据包在到达目的地之前必须经过的中间节点或路由器的IP地址。如果数据包是直接发送到目的地，该字段通常为0.0.0.0。
- Genmask：掩码，用于确定如何匹配目标地址。
- Flags：标志位，指示路由的状态和属性，如UG表示网关路由。
- Metric：度量值，表示数据包在选择路由时的优先级或成本。
- Ref：引用计数，表示有多少个进程或路由条目引用了这个路由。
- Use：使用计数，表示该路由已被使用多少次。
- Iface：接口，表示路由所依附的网络接口。

## netstat -i

`netstat -i` 是用于显示网络接口统计信息的netstat命令选项。它提供了有关每个网络接口的统计数据，包括接收和发送的数据包数量、错误计数、丢弃的数据包数量等等。以下是`netstat -i` 命令输出的一些示例：

```
Kernel Interface table
Iface      MTU  RX-OK RX-ERR RX-DRP RX-OVR    TX-OK TX-ERR TX-DRP TX-OVR Flg
eth0      1500   12345      0      1      0    23456      2      0      0 BMRU
wlan0     1500   56789      3      0      0    67890      1      0      0 BMRU
lo       65536   98765      0      0      0    98765      0      0      0 LRU
```

在这个示例中，每一行表示一个网络接口，包含以下列：

- Iface：接口名称，表示网络接口的名称。
- MTU：最大传输单元，表示网络接口所支持的最大数据包大小。
- RX-OK：接收正常的数据包数量。
- RX-ERR：接收错误的数据包数量。
- RX-DRP：接收丢弃的数据包数量。
- RX-OVR：接收溢出的数据包数量（如果有的话）。
- TX-OK：发送正常的数据包数量。
- TX-ERR：发送错误的数据包数量。
- TX-DRP：发送丢弃的数据包数量。
- TX-OVR：发送溢出的数据包数量（如果有的话）。
- Flg：接口标志，表示网络接口的状态和属性。



## netstat -g

`netstat -g` 是用于显示多播组成员资格信息的netstat命令选项。多播组是一种网络通信模式，其中数据包可以发送到多个接收者，而不是仅发送给单个目标。多播组成员资格表示计算机或网络设备是否加入了某个多播组以接收该组的多播流量。

以下是`netstat -g` 命令输出的一些示例：

```
IPv6/IPv4 Group Memberships
Interface       RefCnt Group
--------------- ------ ---------------------
eth0            1      224.0.0.1
eth0            1      224.0.0.2
eth0            1      224.0.0.251
wlan0           1      224.0.0.1
lo              1      224.0.0.1
```

在这个示例中，每一行表示一个网络接口的多播组成员资格。具体来说，它包含以下列：

- Interface：接口名称，表示网络接口的名称。
- RefCnt：引用计数，表示有多少个进程或应用程序加入了该多播组。
- Group：多播组的IP地址或组名

## netstat -s

`netstat -s` 是用于显示各种网络统计信息的netstat命令选项。它提供了有关网络协议的详细统计数据，包括TCP、UDP、ICMP等。这些统计数据包括接收和发送的数据包数量、错误计数、丢弃的数据包数量、连接建立和关闭的次数等等