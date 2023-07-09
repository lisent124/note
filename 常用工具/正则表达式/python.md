# Python-RE

python中 re模块使用

## 常用函数

**re.compile(pattern,flags)** *对象模式编辑*

### flags 

- **re.A**	ASCII 仅仅ASCII匹配 与re.U unicode对应
- **re.I**	ignoreCase 不区分大小写
- **re.L**	locale 做本地化识别
- **re.M**	multiLine 多行匹配
- **re.S**	special 使 '.'包括换行在内的所有字符
- **re.X**	使匹配过程忽略表达式的空白和注释,此时空白需要用\s来代替



### 匹配返回一个RE对象

- **re.match()**   *如果字符串开头与正则表达式模式匹配，则返回re对象，否则返回None*
- **re.fullmatch()**   *如果整个字符串与正则表达式模式匹配，则返回re对象，否则返回None*
- **re.search()**   *查找正则表达式模式生成匹配的第一个位置，返回re对象*

### 匹配返回多个RE对象

- **findall()**   *匹配所有符合的字符串 返回list*
- **finditer()**  *匹配所有符合的字符串 返回iter，其中iter中是re对象*

## 其他函数

- **spilt(pattern,string,flags)**   

按模式的出现次数拆分字符串 pattern 为分隔符

- **escape(pattern)**  

对模式中的特殊字符进行转义

- **sub(pattern,repl,string,count=0,flags=0)** 

将匹配的字符串用repl替换

subn 与 sub相似但返回元组

repl可以是函数也可以是字符串 如：

```python
def dashrepl(matchobj):
     if matchobj.group(0) == '-': return ' '
     else: return '-'
re.sub('-{1,2}', dashrepl, 'pro----gram-files')
# 返回 'pro--gram files'
re.sub(r'\sAND\s', ' & ', 'Baked Beans And Spam', flags=re.IGNORECASE)
# 返回 'Baked Beans & Spam

```

## re对象

RE对象集合了匹配对象的相关信息

### 位置信息    

**span()**	

返回匹配的字符串在原文中的位置
包括start end

### 内容信息   


可对pattern通过`()`或 `?P<name>` 分隔，使返回的数据结构化



```python
result = re.search('(http\w*)://(.+) ','lisent: https://www.python.org dfasd')
result.group() 
result[0]		#https://www.python.org
result.group(1)	
result[1]		#https
result.group(2)	#www.python.org

result = re.search('(?P<tpye>http\w)://(?P<url>.+) ','lisent: https://www.python.org dfasd')
result.group()		#   https://www.python.org
result.group(1)		#   https
result.group('type')#	https
result.group(2)	    #	www.python.org
result.group('url') #	www.python.org

# 将匹配的字符串集合以set形式输出
```





## 代码示例

```python
import re

string ="""
<div class='thumb-container'>
                    <div class='boxgrid'>
                        <a href="/big.php?i=584470&amp;lang=Chinese"  rel="nofollow"  title="动漫 女孩 花 Brown Hair Brown Eyes 高清壁纸 | 桌面背景">
                            <picture>
                                <source media="(max-width:400px)" srcset="https://images6.alphacoders.com/584/thumb-350-584470.webp">
                                <source media="(max-width:400px)" srcset="https://images6.alphacoders.com/584/thumb-350-584470.jpg">
                                <source srcset="https://images6.alphacoders.com/584/thumbbig-584470.webp">
                                <img class="img-responsive big-thumb"   width="600" height="375" 
                                    src="https://images6.alphacoders.com/584/thumbbig-584470.jpg" alt="动漫 女孩 花 Brown Hair Brown Eyes 高清壁纸 | 桌面背景">
                            </picture>
                        </a>
                    </div>
"""

source = re.compile("<source .+?>",re.M)
div = re.compile("<div .*?>",re.M)

# search 查找正则表达式模式生成匹配的第一个位置，返回re对象
result = source.search(string)
print(result)
# <re.Match object; span=(279, 380), match='<source media="(max-width:400px)" srcset="https:/>

# match 如果字符串开头与正则表达式模式匹配，则返回re对象
result = div.match(string) # None

# fullmatch 如果整个字符串与正则表达式模式匹配，则返回re对象
result = div.fullmatch(string) # re对象

result = div.search(string)
if result != None : print(result.span()) 
# (0, 29)
print(result) 
#<re.Match object; span=(0, 29), match="<div class='thumb-container'>">

# findall 匹配所有符合的字符串 返回list
list = source.findall(string) # list对象


# finditer 匹配所有符合的字符串 返回iter
iter = source.finditer(string)
for i in iter:
    if i != None : print(i.span())
    print(i)

# sub   替换匹配的字符串 subn 与sub相同但返回元组
print(re.sub(r'\sAND\s', ' & ', 'Baked Beans And Spam', flags=re.IGNORECASE))
# Baked Beans & Spam

# split 按模式的出现次数拆分字符串 pattern 为分隔符
list = re.split('\.', '1.2.3sd.a...',flags=0)
print(list) # list对象

# escape(pattern) 对模式中的特殊字符进行转义
print(re.escape('https://www.python.org'))
# https://www\.python\.org

```







