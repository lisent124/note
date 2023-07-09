# Shell 





## Shell环境类型

- Bourne Shell（/usr/bin/sh或/bin/sh）
- Bourne Again Shell（/bin/bash）
- C Shell（/usr/bin/csh）
- K Shell（/usr/bin/ksh）

查看环境类型 

`echo $SHELL`



## 语法

shell 是与python一样空格敏感的语言

### 环境声明

 #!/bin/bash
	

### 注释 

```shell
 #!/bin/bash
 
 echo 单行注释 #单行注释
 echo 多行注释 # 多行注释时 $$ 可以为其他字符
 :<<$$ 
注释内容
注释内容
 $$
 
```

## 变量

### 引用变量 

your_name="qinjx"
echo $your_name
echo ${your_name}

### 只读变量 

readonly 变量名

### 删除变量 

unset 变量名

### 变量类型

#### String 

单双引号皆可，没有引号也行

- 单引号里的任何字符都会原样输出，单引号字符串中的变量是无效的；
- 单引号字串中不能出现单独一个的单引号（对单引号使用转义符后也不行），但可成对出现，作为字符串拼接使用。

不可出现转义字符 任何字符都是原样输出 除了拼接的情况除外

**拼接字符串** 

```shell
greeting="hello, "$your_name" !" 
greeting="hello, $your_name !"
greeting_1='hello, '$your_name' !'
```



**获取字符串长度** 

`${#变量名}`

**截取字符串** 

`${string:0:1}`

**查找子字符串** 

````shell
# 查找字符 i 或 o 的位置(哪个字母先出现就计算哪个)
string="runoob is a great site"
echo `expr index "$string" io`  # 输出 4
````

#### 数组 

`array_name=(value0 value1 value2 value3)`

**读取数组** 

```shell
${array_name[n]} #当n取@或*是可以获取数组中全部元素 
${!array_name[*]} #获取所有键
$array_name #等价于 ${array_name[0]}
```

**关联数组**

作用同python中的字典

```shell
declare -A dict # 先声明 
dict["key"]="string" #赋值 
```

**参数传递**

- $n 的形式读取传递的参数
- $# 传递到脚本的参数个数
- $* 返回传递参数视为一个字符串
- $@ 以空格分隔参数返回多个字符串
- $$ 脚本运行的当前进程ID号
- $!  后台运行的最后一个进程的ID号

## 基本运算

### 算数运算

*bash中不支持简单的数学运行，但可以通过其他命令来实现如awk和expr*

如：`val=expr $name + 12`
**表达式与运算符间需要有空格间隔 **

**乘号前必须加反斜杠才可以实现乘法运算**

### 关系运算

- -eq   *等于*
- -ne  *不等于*
- -gt *大于*
- -lt *小于*
- -ge  *大于等于*
- -le *小于等于*

### 布尔运算 

- !   *非*
- -o  *或*
- -a  *与*

### 逻辑运算

- &&  *AND*
- ||  *OR*

### 字符串运算

- =  *检测两个字符串是否相等*
- -z  *检测字符串长度是否为0 为0 返回True*
- -n *检测字符串长度是否不为 0，不为 0 返回 true*
- $   *检测字符串是否不为空，不为空返回 true*

### 文件测试运算

- -b file	检测文件是否是块设备文件，如果是，则返回 true。	[ -b $file ] 返回 false。
- -c file	检测文件是否是字符设备文件，如果是，则返回 true。	[ -c $file ] 返回 false。
- -d file	检测文件是否是目录，如果是，则返回 true。	[ -d $file ] 返回 false。
- -f file	检测文件是否是普通文件，如果是，则返回 true。	[ -f $file ] 返回 true。
- -g file	检测文件是否设置了 SGID 位，如果是，则返回 true。	[ -g $file ] 返回 false。
- -k file	检测文件是否设置了粘着位(Sticky Bit)，如果是，则返回 true。	[ -k $file ] 返回 false。
- -p file	检测文件是否是有名管道，如果是，则返回 true。	[ -p $file ] 返回 false。
- -u file	检测文件是否设置了 SUID 位，如果是，则返回 true。	[ -u $file ] 返回 false。
- -r file	检测文件是否可读，如果是，则返回 true。	[ -r $file ] 返回 true。
- -w file	检测文件是否可写，如果是，则返回 true。	[ -w $file ] 返回 true。
- -x file	检测文件是否可执行，如果是，则返回 true。	[ -x $file ] 返回 true。
- -s file	检测文件是否为空（文件大小是否大于0），不为空返回 true。	[ -s $file ] 返回 true。
- -e file	检测文件（包括目录）是否存在，如果是，则返回 true。	[ -e $file ] 返回 true。





## 流程控制 

### test命令 

*用于检查某个条件是否成立*

`test 条件`

#### if 

`if condition; then command1 ... command; fi;`
**; 可被换行符代替**

condition 用[  ] 框起来，且[  ]与条件间隔空格

或用test判断

或(()) 如果使用 ((...)) 作为判断语句，大于和小于可以直接使用 > 和 <

```shell
if (( 1 < 0 ))
then echo hello
fi
```

```shell
if [ $(ps -ef | grep -c "ssh") -gt 1 ]; then echo "true"; fi
```

#### if else -if else 

`if condition1 ; then command1 ;elif condition2 ;then conmand2 ; fi`

```shell
a=10
b=20
if test $a -eq $b 
then
   echo "a 等于 b"
elif [ $a -gt $b ]
then
   echo "a 大于 b"
elif (( $a < $b ))
then
   echo "a 小于 b"
else
   echo "没有符合的条件"
fi
```





#### for循环 

`for var in item1 item2 ... item; do command1; command2… done;`

```shell
for loop in 1 2 3 4 5
do
    echo "The value is: $loop"
done
```

#### while循环 

`while condition ; do command ; done;`

```shell
int=1
while(( $int<=5 ))
do
    echo $int
    let "int++"
done
```

#### until循环 

同while 循环相反
`until condition ; do command ; done;`

#### case多选择

与switch... case 语句类似

```shell
site="runoob"
case "$site" in
   "runoob") echo "菜鸟教程"
   ;;
   "google") echo "Google 搜索"
   ;;
   *) echo "其他"
   ;;
esac
```



#### 中断 

break 
continue

## 函数 

[ function ] funname [()]
{
    action;
    [return int;]
}
注意 [] 中的内容可以省略

```shell
demoFun(){
    echo "这是我的第一个 shell 函数!"
}
```


