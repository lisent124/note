# JAVA IO

Java.io 包几乎包含了所有操作输入、输出需要的类。所有这些流类代表了输入源和输出目标。

Java.io 包中的流支持很多种格式，比如：基本类型、对象、本地化字符集等等。

传统IO也称BIO 即blocked IO **阻塞型IO**

## IO 流

IO流主要分为两大类，字节流和字符流。

IO流的本质是数据传输，并且流是单向的。

字节流和字符流的区别：

- 读写单位的不同：字节流以字节（8bit）为单位。字符流以字符为单位，根据码表映射字符，一次可能读多个字节。

- 处理对象不同：字节流可以处理任何类型的数据，如图片、avi等，而字符流只能处理字符类型的数据。



## IO包中的结构

树的叶子节点才是可实例化对象，其他的都是抽象对象

**大概结构**

java.lang.Object 

- java.io.**File** (implements java.lang.Comparable<T>, java.io.Serializable) 
- java.io.**RandomAccessFile** (implements java.io.Closeable, java.io.DataInput, java.io.DataOutput) 
- java.io.**InputStream** (implements java.io.Closeable) 
  - java.io.ByteArrayInputStream 
  - java.io.**FileInputStream** 
  - java.io.FilterInputStream 
    - java.io.**BufferedInputStream** 
    - java.io.**DataInputStream** (implements java.io.DataInput) 
  - java.io.**ObjectInputStream** (implements java.io.ObjectInput, java.io.ObjectStreamConstants) 
  - java.io.PipedInputStream 
  - java.io.StringBufferInputStream 
- java.io.**OutputStream** (implements java.io.Closeable, java.io.Flushable) 
  - java.io.ByteArrayOutputStream 
  - java.io.**FileOutputStream** 
  - java.io.FilterOutputStream 
    - java.io.**BufferedOutputStream** 
    - java.io.DataOutputStream (implements java.io.DataOutput) 
    - java.io.**PrintStream** (implements java.lang.Appendable, java.io.Closeable) 
  - java.io.ObjectOutputStream (implements java.io.ObjectOutput, java.io.ObjectStreamConstants) 
  - java.io.PipedOutputStream 
- java.io.**Reader** (implements java.io.Closeable, java.lang.Readable) 
  - java.io.BufferedReader 
    - java.io.LineNumberReader 
  - java.io.CharArrayReader 		
  - java.io.FilterReader 
    - java.io.PushbackReader 
  - java.io.**InputStreamReader** 
    - java.io.**FileReader** 
  - java.io.PipedReader 
  - java.io.StringReader 
- java.io.**Writer** (implements java.lang.Appendable, java.io.Closeable, java.io.Flushable) 
  - java.io.BufferedWriter 
  - java.io.CharArrayWriter 
  - java.io.FilterWriter 
  - java.io.**OutputStreamWriter** 
    - java.io.**FileWriter** 
  - java.io.PipedWriter 
  - java.io.**PrintWriter** 
  - java.io.StringWriter 





### 字节流

字节流一切IO操作都是以byte为单位

其特征是以**Stream**结尾



#### `InputStream`

这个抽象类是表示输入字节流的所有类的超类

需要定义`InputStream`子类的应用程序必须始终提供一种返回输入的下一个字节的方法。

**方法摘要**

| Modifier and Type | 方法                                      | 描述                                                         |
| ----------------- | ----------------------------------------- | ------------------------------------------------------------ |
| `int`             | `available()`                             | 从下一次调用此输入流的方法返回可从该输入流读取（或跳过）的字节数，而不会阻塞。 |
| `void`            | `close()`                                 | 关闭此输入流并释放与流相关联的任何系统资源。                 |
| `void`            | `mark(int readlimit)`                     | 标记此输入流中的当前位置。                                   |
| `boolean`         | `markSupported()`                         | 测试此输入流是否支持 `mark`和 `reset`方法。                  |
| `abstract int`    | `read()`                                  | 从输入流读取数据的下一个字节。                               |
| `int`             | `read(byte[] b)`                          | 从输入流中读取一些字节数，并将它们存储到缓冲器阵列 `b` 。    |
| `int`             | `read(byte[] b,  int off, int len)`       | 从输入流读取最多 `len`个字节的数据到字节数组。               |
| `byte[]`          | `readAllBytes()`                          | 从输入流读取所有剩余字节。                                   |
| `int`             | `readNBytes(byte[] b,  int off, int len)` | 将所请求的字节数从输入流读入给定的字节数组。                 |
| `void`            | `reset()`                                 | 将此流重新定位到最后在此输入流上调用 `mark`方法时的位置。    |
| `long`            | `skip(long n)`                            | 跳过并丢弃来自此输入流的 `n`字节的数据。                     |
| `long`            | `transferTo(OutputStream out)`            | 从该输入流中读取所有字节，并按读取的顺序将字节写入给定的输出流。 |



#### `OutputStream`

这个抽象类是表示字节输出流的所有类的超类。

输出流接收输出字节并将其发送到某个接收器。

需要定义`OutputStream`子类的应用程序必须至少提供一个写入一个字节输出的方法。 

**方法摘要**

| Modifier and Type | 方法                                 | 描述                                                         |
| ----------------- | ------------------------------------ | ------------------------------------------------------------ |
| `void`            | `close()`                            | 关闭此输出流并释放与此流相关联的任何系统资源。               |
| `void`            | `flush()`                            | 刷新此输出流并强制任何缓冲的输出字节被写出。                 |
| `void`            | `write(byte[] b)`                    | 将 `b.length`字节从指定的字节数组写入此输出流。              |
| `void`            | `write(byte[] b,  int off, int len)` | 从指定的字节数组写入 `len`字节，从偏移量 `off`开始输出到此输出流。 |
| `abstract void`   | `write(int b)`                       | 将指定的字节写入此输出流。                                   |



### 字符流

字符流操作以 char 为单位

其特征是以**Reader** 或者 **Writer**结尾



#### `Reader`

用于读取字符流的抽象类。 

子类必须实现的唯一方法是`read(char  []，int，int)`和`close()`。

 然而，大多数子类将覆盖这里定义的一些方法，以便提供更高的效率，附加的功能或两者。 

**方法摘要**

| Modifier and Type | 方法                                   | 描述                                   |
| ----------------- | -------------------------------------- | -------------------------------------- |
| `abstract void`   | `close()`                              | 关闭流并释放与之相关联的任何系统资源。 |
| `void`            | `mark(int readAheadLimit)`             | 标记流中的当前位置。                   |
| `boolean`         | `markSupported()`                      | 告诉这个流是否支持mark（）操作。       |
| `int`             | `read()`                               | 读一个字符                             |
| `int`             | `read(char[] cbuf)`                    | 将字符读入数组。                       |
| `abstract int`    | `read(char[] cbuf,  int off, int len)` | 将字符读入数组的一部分。               |
| `int`             | `read(CharBuffer target)`              | 尝试将字符读入指定的字符缓冲区。       |
| `boolean`         | `ready()`                              | 告诉这个流是否准备好被读取。           |
| `void`            | `reset()`                              | 重置流。                               |
| `long`            | `skip(long n)`                         | 跳过字符                               |



#### `Writer`

用于写入字符流的抽象类。 

子类必须实现的唯一方法是 `write(char  []，int，int)`，flush（）和`close()`。  

然而，大多数子类将覆盖这里定义的一些方法，以便提供更高的效率，附加的功能或两者。 

**方法摘要**

 | Modifier and Type | 方法                                            | 描述                                 |
| ----------------- | ----------------------------------------------- | ------------------------------------ |
| `Writer`          | `append(char c)`                                | 将指定的字符附加到此作者。           |
| `Writer`          | `append(CharSequence csq)`                      | 将指定的字符序列附加到此作者。       |
| `Writer`          | `append(CharSequence csq, int start,  int end)` | 将指定字符序列的子序列附加到此作者。 |
| `abstract void`   | `close()`                                       | 关闭流，先刷新。                     |
| `abstract void`   | `flush()`                                       | 刷新流。                             |
| `void`            | `write(char[] cbuf)`                            | 写入一个字符数组。                   |
| `abstract void`   | `write(char[] cbuf,  int off, int len)`         | 写入字符数组的一部分。               |
| `void`            | `write(int c)`                                  | 写一个字符                           |
| `void`            | `write(String str)`                             | 写一个字符串                         |
| `void`            | `write(String str,  int off, int len)`          | 写一个字符串的一部分。               |



### File 文件流

包括四个可实例化对象

- `java.io.FileInputStream`
- `java.io.FileOutputStream`
- `java.io.FileReader`
- `java.io.FileWriter`

其操作对象为一个文件，在构造该对象时需要提供文件地址，或者一个文件对象

**构造方法**

- `FileXXX(String name)` 文件地址
- `FileXXX(File file)` 文件对象

### 转换流

将字节流转换成字符流

| 对象                         | 构造方法                               |
| ---------------------------- | -------------------------------------- |
| `java.io.InputStreamReader`  | `InputStreamReader(InputStream in)`    |
| `java.io.OutputStreamWriter` | `OutputStreamWriter(OutputStream out)` |



### Buffer 缓冲流

将数据放入缓冲区中以提供高效快捷的IO

可以指定缓冲区大小，或者可以使用默认大小。 默认值足够大，可用于大多数用途。 

一般需要依靠文件流

- `java.io.BufferedReader`
- `java.io.BufferedWriter`
- `java.io.BufferedInputStream`
- `java.io.BufferedOutputStream`

**构造方法**

- `BufferXXX(XXX x)`

如 `BufferReader(Reader in)`



### Data 数据流

数据流可将java中基本数据类型转化为字节流

| 对象                       | 构造方法                             |
| -------------------------- | ------------------------------------ |
| `java.io.DataInputStream`  | `DataInputStream(InputStream in)`    |
| `java.io.DataOutputStream` | `DataOutputStream(OutputStream out)` |



### Print 输出流

- `java.io.PrintWriter`
- `java.io.PrintStream`

将对象的格式表示打印到文本输出流。这个类实现所有的`print`中发现的方法`PrintStream`。它不包含用于编写原始字节的方法，程序应使用未编码的字节流。

不像`PrintStream`类，如果启用自动刷新，将只有当一个做`println`  ， `printf` ，或`format`被调用的方法，而不是当一个换行符恰好是输出。  这些方法使用平台自己的行分隔符而不是换行符。 

**`PrintStream` 构造方法**

| Constructor                                         | 描述                                                         |
| --------------------------------------------------- | ------------------------------------------------------------ |
| `PrintWriter(File file)`                            | 使用指定的文件创建一个新的PrintWriter，而不需要自动的线路刷新。 |
| `PrintWriter(File file, String csn)`                | 使用指定的文件和字符集创建一个新的PrintWriter，而不需要自动进行线条刷新。 |
| `PrintWriter(OutputStream out)`                     | 从现有的OutputStream创建一个新的PrintWriter，而不需要自动线路刷新。 |
| `PrintWriter(OutputStream out,  boolean autoFlush)` | 从现有的OutputStream创建一个新的PrintWriter。                |
| `PrintWriter(Writer out)`                           | 创建一个新的PrintWriter，没有自动线冲洗。                    |
| `PrintWriter(Writer out,  boolean autoFlush)`       | 创建一个新的PrintWriter。                                    |
| `PrintWriter(String fileName)`                      | 使用指定的文件名创建一个新的PrintWriter，而不需要自动执行行刷新。 |
| `PrintWriter(String fileName, String csn)`          | 使用指定的文件名和字符集创建一个新的PrintWriter，而不需要自动线路刷新。 |

**`PrintStream`构造方法与上面相似**

将对象的格式表示打印到文本输出流。这个类实现所有的`print`中发现的方法[`PrintStream`](../../java/io/PrintStream.html) 。它不包含用于编写原始字节的方法，程序应使用未编码的字节流。

不像[`PrintStream`](../../java/io/PrintStream.html)类，如果启用自动刷新，将只有当一个做`println`  ， `printf` ，或`format`被调用的方法，而不是当一个换行符恰好是输出。  这些方法使用平台自己的行分隔符而不是换行符。 

### 对象专属流

该对象可以将序列化的**对象储存**到流中

- `java.io.ObjectInputStream`
- `java.io.ObjectOutputStream`

**构造方法**

- **`ObjectXXXStream(XXXStream x)`**

#### **Serializable**接口

可以区分两个类是否相同

- 起到 **标识** 的作用，标志的作用，java虚拟机看到这个类实现了这个接口，可能会对这个类进行特殊待遇。
- Serializable这个标志接口是给java虚拟机参考的，java虚拟机看到这个接口之后，会为该类自动生成一个序列化版本号。
- 自动序列化版本号会根据该类的代码生成一个类似md5的编码，所以每次对该类的更改都会生成一个新的序列化版本号。



### Pipe 管道流

在Java中，管道流（Pipe）是一种用于在**线程之间进行通信的机制**。

它允许一个线程将数据写入管道，而另一个线程可以从该管道中读取数据。

管道流主要用于同一Java虚拟机内的线程之间进行通信。

- `PipedInputStream`
- `PipedOutputStream`
- `PipedReader`
- `PipedWriter`

无论使用字节管道还是字符管道，在一个线程中写入的数据可以被另一个线程读取，这样实现了线程间的通信。注意，若读线程尝试在写线程关闭输出流之前读取数据，可能会导致阻塞。因此，在使用管道流时，确保适时关闭相关的输入输出流，以避免潜在的阻塞问题。

```java
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public class PipeExample {
    public static void main(String[] args) throws IOException {
        PipedReader reader = new PipedReader();
        PipedWriter writer = new PipedWriter();

        // 将输入流和输出流连接起来
        reader.connect(writer);

        // 创建写数据的线程
        Thread writerThread = new Thread(() -> {
            try {
                String message = "Hello, Pipe!";
                writer.write(message);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // 创建读数据的线程
        Thread readerThread = new Thread(() -> {
            try {
                int data;
                while ((data = reader.read()) != -1) {
                    System.out.print((char) data);
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // 启动线程
        writerThread.start();
        readerThread.start();
    }
}

```





## File 对象

File类不能完成文件的读和写，其只是文件或者文件夹的抽象表示。

**构造方法**

| Constructor                          | 描述                                                         |
| ------------------------------------ | ------------------------------------------------------------ |
| `File(File parent, String child)`    | 从父抽象路径名和子路径名字符串创建新的 `File`实例。          |
| `File(String pathname)`              | 通过将给定的路径名字符串转换为抽象路径名来创建新的 `File`实例。 |
| `File(String parent,  String child)` | 从父路径名字符串和子路径名字符串创建新的 `File`实例。        |
| `File(URI uri)`                      | 通过将给定的 `file:` URI转换为抽象路径名来创建新的 `File`实例。 |



## RandomAccessFile 对象

RandomAccessFile是Java IO中用于随机访问文件的类，它允许文件内容的任意读写操作，而不仅限于顺序读写。

RandomAccessFile提供了一系列方法，允许我们根据指定的文件位置进行读取和写入操作，以及查询和设置文件指针的位置。

**构造方法**

| Constructor                                   | 描述                                                         |
| --------------------------------------------- | ------------------------------------------------------------ |
| `RandomAccessFile(File file, String mode)`    | 创建一个随机访问文件流，从`File`参数指定的文件读取，并可选地写入。 |
| `RandomAccessFile(String name,  String mode)` | 创建随机访问文件流，以从中指定名称的文件读取，并可选择写入文件。 |



**常用方法**

| Modifier and Type | 方法 | 描述 |
| --------------- | ----------------------------- | --------------------------------------------------- |
| `void` | `seek(long pos)` | 设置文件指针偏移，从该文件的开头测量，发生下一次读取或写入。 |
| `long` | `getFilePointer()` | 返回此文件中的当前偏移量。 |
| `long` | `length()`         | 返回此文件的长度。         |
| `int` | `read()`         | 从该文件读取一个字节的数据。                            |
| `int` | `read(byte[] b)` | 从该文件读取最多 `b.length`个字节的数据到一个字节数组。 |
| `void` | `write(byte[] b,  int off, int len)` | 从指定的字节数组写入 `len`个字节，从偏移量 `off`开始写入此文件。 |
| `void` | `write(int b)`                       | 将指定的字节写入此文件。                                     |




## Closeable 接口

都是可关闭的，都有 close() 方法。

流是一个管道，这个是内存和硬盘之间的通道，用完之后一定要关闭，不然会耗费(占用)很多资源。

养成好习惯，用完流一定要关闭。



## Flushable 接口

IO操作通常会使用缓冲区来提高性能，但并不会立即将数据写入目标设备，而是在缓冲区满了或者在适当的时机触发刷新操作才会将数据写入。

养成一个好习惯，输出流在最终输出之后，一定要记得flush()刷新一下。

需要注意的是，**过度使用flush()可能会降低IO操作的性能**，因为频繁的刷新会增加IO操作的开销。

因此，只有在确实需要数据立即写入目标设备的情况下才应该使用flush()，否则让缓冲区自动刷新会更高效。





# JAVA NIO

NIO（New I/O，非阻塞IO）则在 `java.nio` 中使用，它涉及到一些新的IO类和概念，如Channel、Buffer、Selector等。

NIO提供了一种基于通道（Channel）和缓冲区（Buffer）的IO模型

通道表示打开到 IO 设备（例如：文件、套接字）的连接。

若需要使用 NIO 系统，需要获取用于连接 IO 设备的通道以及用于容纳数据的缓冲区。

然后操作缓冲区，对数据进行处理。简而言之，**Channel 负责传输，Buffer 负责存储**



## NIO包中结构

- java.nio.Buffer 
  - java.nio.**ByteBuffer** (implements java.lang.Comparable<T>) 
    - java.nio.**MappedByteBuffer** 
  - java.nio.**CharBuffer** (implements java.lang.Appendable, java.lang.CharSequence, java.lang.Comparable<T>, java.lang.Readable) 
  - java.nio.FloatBuffer (implements java.lang.Comparable<T>) 
  - java.nio.IntBuffer (implements java.lang.Comparable<T>) 
- java.nio.channels.spi.AbstractInterruptibleChannel 
  - java.nio.channels.**FileChannel**
  - java.nio.channels.SelectableChannel (implements java.nio.channels.Channel) 
    - java.nio.channels.spi.AbstractSelectableChannel 
      - java.nio.channels.**DatagramChannel** 
      - java.nio.channels.Pipe.SinkChannel 
      - java.nio.channels.Pipe.SourceChannel
      - java.nio.channels.**ServerSocketChannel**
      - java.nio.channels.**SocketChannel** 
- java.nio.channels.Selector (implements java.io.Closeable) 



## Buffer 缓冲区

缓冲器是特定原始类型的元素的线性有限序列。 除了其内容，缓冲区的基本属性是其容量，限制和位置

**方法详情**

| Modifier and Type  | 方法                        | 描述                                                                   |
| -------------------------- | -------------------------- | ------------------------------------------------------------ |
| `int`          | **`capacity()`**            | 返回此缓冲区的容量。                                         |
| `Buffer`       | **`clear()`**               | 清除此缓冲区。                                               |
| `abstract Buffer`  | `duplicate()`               | 创建一个共享此缓冲区内容的新缓冲区。                         |
| `Buffer`           | **`flip()`**                | 翻转这个缓冲区。                                             |
| `abstract boolean` | `hasArray()`                | 告诉这个缓冲区是否由可访问的数组支持。                       |
| `boolean`          | `hasRemaining()`            | 告诉当前位置和极限之间是否存在任何元素。                     |
| `abstract boolean` | `isDirect()`                | 告诉这个缓冲区是否是 *direct*   |
| `abstract boolean` | `isReadOnly()`              | 告知这个缓冲区是否是只读的。                                 |
| `int`          | **`limit()`**               | 返回此缓冲区的限制。                                         |
| `Buffer`           | `limit(int newLimit)`       | 设置此缓冲区的限制。                                         |
| `Buffer`           | **`mark()`**                | 将此缓冲区的标记设置在其位置。                               |
| `int`          | **`position()`**            | 返回此缓冲区的位置。                                         |
| `Buffer`           | `position(int newPosition)` | 设置这个缓冲区的位置。                                       |
| `int`              | `remaining()`               | 返回当前位置和限制之间的元素数。                             |
| `Buffer`           | `reset()`                   | 将此缓冲区的位置重置为先前标记的位置。                       |
| `Buffer`           | `rewind()`                  | 倒带这个缓冲区。                                             |
| `abstract Buffer`  | `slice()`                   | 创建一个新的缓冲区，其内容是此缓冲区内容的共享子序列。       |

**该类的每个子类定义了一下操作**

- `allocate(int capacity)`: 静态方法，用于分配一个新的Buffer。capacity为分配的内存大小。	

- `get()`: 从Buffer中读取一个字节数据，并将position向后移动一位。
- `put()`: 向Buffer中写入一个字节数据，并将position向后移动一位。

## channel  通道

Channel（通道）是用于读取和写入数据的实体。它代表了与IO源或IO目标之间的连接，例如文件、网络套接字等。

在Java NIO中，常见的Channel子类有：

- `FileChannel`：用于文件的读写操作。
- `SocketChannel`：用于TCP网络通信的读写操作。
- `ServerSocketChannel`：用于TCP服务器端的连接监听。
- `DatagramChannel`：用于UDP网络通信的读写操作。



### `FileChannel` 

以下是`FileChannel`的一些详细信息和常用方法：

1. **获取`FileChannel`：**

   - 通过`RandomAccessFile`获取：通过创建`RandomAccessFile`对象并调用其`getChannel()`方法获取`FileChannel`对象。可以指定读写模式，如"r"表示只读模式，"rw"表示读写模式。
   - 通过`FileOutputStream`获取：通过创建`FileOutputStream`对象并调用其`getChannel()`方法获取`FileChannel`对象。
   - 通过FileChannel的静态方法 `open()` 获取`FileChannel`对象

2. **读取和写入数据：**

   - `int read(ByteBuffer dst)`: 从FileChannel中读取数据到ByteBuffer中，并返回读取的字节数。通常在非阻塞模式下，read方法返回0表示没有更多的数据可读，-1表示已到达流的末尾。
   - `int write(ByteBuffer src)`: 将ByteBuffer中的数据写入到FileChannel中，并返回写入的字节数。

3. 文件位置（Position）：

   - `long position()`: 获取当前的文件位置。
   - `FileChannel position(long newPosition)`: 设置文件位置为给定的值。后续的读取和写入操作将从该位置开始。

4. 文件截取（Truncate）：

   - `FileChannel truncate(long size)`: 截取文件到给定的大小。如果文件当前大小大于给定的大小，那么超出给定大小部分的内容将被删除。

5. **文件映射（File Mapping）：**

   - `MappedByteBuffer map(FileChannel.MapMode mode, long position, long size)`: 将文件的一部分区域映射到内存中。这样可以直接在内存中对文件进行读写，避免了频繁的系统调用，提高了IO操作的效率。

6. **传输数据（Transfer）：**

   - `long transferTo(long position, long count, WritableByteChannel target)`: 将指定区域的数据传输到另一个通道（WritableByteChannel）中。
   - `long transferFrom(ReadableByteChannel src, long position, long count)`: 将数据从另一个通道（ReadableByteChannel）传输到FileChannel中。

7. 锁定（Locking）：

   - `FileLock lock()`: 对文件进行独占锁定，确保其他进程无法访问该文件。
   - `FileLock tryLock()`: 尝试对文件进行锁定，如果文件已被锁定，将返回null而不是阻塞等待。

   以下是通过FileChannel进行文件复制的一个**列子**

```java
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class FileChannelExample {
    public static void main(String[] args) {
        try {
            // 通过FileOutputStream获取FileChannel
            RandomAccessFile sourceFile = new RandomAccessFile("source.txt", "r");
            FileChannel sourceChannel = sourceFile.getChannel();
            // 通过FileChannel.open 获取t
            FileChannel targetChannel =
                FileChannel.open(Paths.get("target.txt"),
                                 StandardOpenOption.CREATE,
                                 StandardOpenOption.WRITE)
			// 方法一 直接缓冲区
            targetChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
			// 或者可以使用下面这种方式
    		////sourceChannel.transferTo(0, sourceChannel.size(), targetChannel);
            
            //方法二 非直接缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            // 将通道中的数据存入缓冲区中
            while (sourceChannel.read(byteBuffer) != -1) {  // fisChannel 中的数据读到 byteBuffer 缓冲区中
                byteBuffer.flip();  // 切换成读数据模式
                // 将缓冲区中的数据写入通道
                targetChannel.write(byteBuffer);
                byteBuffer.clear();  // 清空缓冲区
            }
            
            /**
             * 方法三 内存映射文件
             * 这种方式缓冲区是直接建立在物理内存之上的
             * 所以我们就不需要通道了
             */
            MappedByteBuffer inMapped = 
                sourceChannel.map(FileChannel.MapMode.READ_ONLY, 0, sourceChannel.size());
            MappedByteBuffer outMapped = 
                targetChannel.map(FileChannel.MapMode.READ_WRITE, 0, targetChannel.size());

            // 直接对缓冲区进行数据的读写操作
            byte[] dst = new byte[inMapped.limit()];
            inMapped.get(dst);  // 把数据读取到 dst 这个字节数组中去
            outMapped.put(dst); // 把字节数组中的数据写出去

            
            
            sourceChannel.close();
            targetChannel.close();
            sourceFile.close();

            System.out.println("文件复制完成！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

```



### SocketChannel

SocketChannel是Java NIO中用于TCP网络通信的通道（Channel）。

它是NIO中的一种Channel实现，提供了非阻塞的网络IO操作，用于在客户端和服务器之间进行数据传输。

SocketChannel通常用于创建网络连接并进行读写操作。

以下是SocketChannel的一些详细信息和常用方法：

1. 打开SocketChannel：
   - `SocketChannel.open()`: 静态方法，用于打开一个新的SocketChannel实例。

2. **连接服务器：**
   - `boolean connect(SocketAddress remote)`: 连接到指定的服务器地址。
   - `boolean finishConnect()`: 完成连接操作，非阻塞模式下需要调用此方法确认连接是否成功。

3. **读取和写入数据：**
   - `int read(ByteBuffer dst)`: 从SocketChannel中读取数据到ByteBuffer中，并返回读取的字节数。通常在非阻塞模式下，read方法返回0表示没有更多的数据可读，-1表示连接已关闭。
   - `int write(ByteBuffer src)`: 将ByteBuffer中的数据写入到SocketChannel中，并返回写入的字节数。

4. **设置非阻塞模式**：
   - `configureBlocking(boolean block)`: 设置SocketChannel为阻塞或非阻塞模式。默认情况下，SocketChannel是阻塞模式，可以通过设置为非阻塞模式实现异步IO操作。

5. 关闭SocketChannel：
   - `void close()`: 关闭SocketChannel，释放相关资源。

SocketChannel可以与Selector（选择器）一起使用，实现非阻塞的多路复用IO。

通过Selector，一个线程可以管理多个SocketChannel的IO操作，从而提高了网络编程的并发处理能力。

在非阻塞模式下，SocketChannel的读写操作不会阻塞线程，可以在IO操作进行的同时执行其他任务。

以下是一个简单的示例，演示了如何使用**SocketChannel连接服务器并进行数据读写**：

```java
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketChannelExample {
    public static void main(String[] args) {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false); // 设置为非阻塞模式

            InetSocketAddress serverAddress = new InetSocketAddress("localhost", 8080);
            socketChannel.connect(serverAddress);

            while (!socketChannel.finishConnect()) {
                // 等待连接完成
            }
			// 发送一条消息
            String message = "Hello, Server!";
            ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
            while (buffer.hasRemaining()) {
                socketChannel.write(buffer);
            }
            buffer.clear();
            
            // 读取返回的数据
            int bytesRead;
            while ((bytesRead = socketChannel.read(buffer)) != -1) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                    System.out.print((char) buffer.get());
                }
                buffer.clear();
            }

            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

