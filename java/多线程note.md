# Java多线程

Java 给多线程编程提供了内置的支持。 一条线程指的是进程中一个单一顺序的控制流，一个进程中可以并发多个线程，每条线程并行执行不同的任务。

## 概念

- **程序（program）**

  程序是为完成特定任务、用某种语言编写的一组指令的集合。即指一段静态的代码（还没有运行起来），静态对象。

- **进程（process）**

  进程是程序的一次执行过程，也就是说程序运行起来了，加载到了内存中，并占用了CPU的资源。这是一个动态的过程：有自身的产生、存在和消亡的过程，这也是进程的生命周期。

  进程是系统资源分配的单位，系统在运行时会为每个进程分配不同的内存区域。

- **线程（thread）**

  进程可进一步细化为线程，是一个程序内部的执行路径。

  若一个进程同一时间并行执行多个线程，那么这个进程就是支持多线程的。

  线程是CPU调度和执行的单位，每个线程拥有独立的运行栈和程序计数器（pc），线程切换的开销小。

  一个进程中的多个线程共享相同的内存单元/内存地址空间——他们从同一堆中分配对象，可以访问相同的变量和对象。

- **CPU单核和多核的理解**

  **单核**的CPU是一种假的多线程，因为在一个时间单元内，也只能执行一个线程的任务。同时间段内有多个线程需要CPU去运行时，CPU也只能交替去执行多个线程中的一个线程，但是由于其执行速度特别快，因此感觉不出来。

  **多核**的CPU才能更好的发挥多线程的效率。

  对于**Java**应用程序java.exe来讲，至少会存在三个线程：

  - main()主线程
  - gc()垃圾回收线程
  - 异常处理线程

- **Java线程的分类**：用户线程 和 守护线程

  - Java的gc()垃圾回收线程就是一个守护线程
  - 守护线程是用来服务用户线程的，通过在start()方法前调用thread.setDaemon(true)可以吧一个用户线程变成一个守护线程。

- **并行和并发**

  - **并行**：多个CPU同时执行多个任务。比如，多个人做不同的事。
  - **并发**：一个CPU（采用时间片）同时执行多个任务。

- **多线程的优点**

  1. 提高应用程序的响应。堆图像化界面更有意义，可以增强用户体验。
  2. 提高计算机系CPU的利用率。
  3. 改善程序结构。将既长又复杂的进程分为多个线程，独立运行，利于理解和修改。

- **何时需要多线程**

  - 程序需要同时执行两个或多个任务。
  - 程序需要实现一些需要等待的任务时，如用户输入、文件读写操作、网络操作、搜索等。
  - 需要一些后台运行的程序时。



## Thread 类

多线程可以通过Java中的java.lang.Thread类来体现

下表列出了Thread类的一些重要方法：

| **序号** | **方法**                                        | **描述**                                                     |
| :------- | :---------------------------------------------- | :----------------------------------------------------------- |
| 1        | **public void start()**                         | 使该线程开始执行；**Java** 虚拟机调用该线程的 run 方法。     |
| 2        | **public void run()**                           | 如果该线程是使用独立的 Runnable 运行对象构造的，则调用该 Runnable 对象的 run 方法；否则，该方法不执行任何操作并返回。 |
| 3        | **public final void setName(String name)**      | 改变线程名称，使之与参数 name 相同。                         |
| 4        | **public final void setPriority(int priority)** | 更改线程的优先级。                                           |
| 5        | **public final void setDaemon(boolean on)**     | 将该线程标记为守护线程或用户线程。                           |
| 6        | **public final void join(long millisec)**       | 等待该线程终止的时间最长为 millis 毫秒。                     |
| 7        | **public void interrupt()**                     | 中断线程。                                                   |
| 8        | **public final boolean isAlive()**              | 测试线程是否处于活动状态。                                   |

上述方法是被 Thread 对象调用的，下面表格的方法是 Thread 类的静态方法。

| **序号** | **方法**                                      | 描述                                                         |
| :------- | :-------------------------------------------- | :----------------------------------------------------------- |
| 1        | **public static void yield()**                | 暂停当前正在执行的线程对象，并执行其他线程。                 |
| 2        | **public static void sleep(long millisec)**   | 在指定的毫秒数内让当前正在执行的线程休眠（暂停执行），此操作受到系统计时器和调度程序精度和准确性的影响。 |
| 3        | **public static boolean holdsLock(Object x)** | 当且仅当当前线程在指定的对象上保持监视器锁时，才返回 true。  |
| 4        | **public static Thread currentThread()**      | 返回对当前正在执行的线程对象的引用。                         |
| 5        | **public static void dumpStack()**            | 将当前线程的堆栈跟踪打印至标准错误流。                       |

***Thread源码在同级目录下***



## 实现方法

### 继承Thread

通过继承Thread并实现其中run方法实现多线程

```java
class ThreadTest extends Thread{
    @Override
    public void run() {
        System.out.println("hello ThreadTest");
    }
}

// 启动方法
new ThreadTest().start()

```



### 实现Runnable

```java
class RunTest implements Runnable{
    @Override
    public void run() {
        System.out.println("hello RunTest");
    }
}
// 启动方法 

new Thread(new RunTest()).start()
```

### 实现Callable

**与Runnable相比，Callable功能更强大**

1. 相比run()方法，可以有返回值
2. 方法可以抛出异常
3. 支持泛型的返回值
4. 需要借助FutureTask类，比如获取返回结果



```java
class CallTest implements Callable<Object> {
    @Override
    public Boolean call() {
        System.out.println("hello "+Thread.currentThread().getName());
        return Thread.currentThread().isAlive();
    }
}
// 启动方法 
FutureTask<String> threadTask= new FutureTask<>(new CallTest());
new Thread(threadTask).start

```

### Lambda 匿名类



```java
new Thread(()=>{
    System.out.println("hello lambda");
}).start()
```



## 线程池

对线程的同一种实现，若经常创建和销毁、使用量特别大的资源、比如并发情况下的线程、对性能影响很大。

这时候可以使用线程池，提前创建好多个线程，放入线程池中，使用时直接获取，使用完放回池中。可以避免频繁创建销毁、实现重复利用

使用需要用到 `Executors.newFixedThreadPool()` 

```java
//1.提供指定线程数量的线程池
ExecutorService service = Executors.newFixedThreadPool(10);
//2.执行指定的线程的操作。需要提供实现Runnable接口或Callable接口实现类的对象。
service.execute(new Thread()); //适合用于Runnable
//        service.submit(); 适合适用于Callable
//关闭线程池
service.shutdown();
```



## 同步问题

多线程会共享进程资源，当多个线程并行操作同一个资源时会出现数据不同步的问题，java提出一些解决的方案。





### Synchronized 同步

Synchronized 是阻塞同步的，只能用在实例方法、静态方法和代码块，不能用于变量或构造方法。

缺点：操作同步代码时，只能有一个线程参与，其他线程等待。相当于时一个单线程的过程，效率低。

**Synchronized 代码块同步**

synchronized(同步监视器){需要被同步的代码}

```java
public class SynchronizedExample {
    private int count = 0;
    public void increment() {
        synchronized (this) { // 使用同步代码块
            count++;
        }
    }
    public int getCount() {
        synchronized (this) { // 使用同步代码块
            return count;
        }
    }
}

```

**Synchronized 注释同步**

```java
public synchronized void increment() {
    count++;S
}

public synchronized int getCount() {
    return count;
}

```



### ReentrantLock 可重入锁

`ReentrantLock` 是 Java 并发包（`java.util.concurrent`）中提供的一种可重入锁（Reentrant Lock）实现。

与使用 `synchronized` 关键字进行同步控制相比，`ReentrantLock` 提供了更灵活和可扩展的同步机制。

```java
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
    private ReentrantLock lock = new ReentrantLock();

    public void performTask() {
        lock.lock(); // 获取锁
        try {
            // 执行需要同步的操作
        } finally {
            lock.unlock(); // 释放锁
        }
    }
}

```

### Atomic 原子操作

`java.util.concurrent.atomic` 包中的原子类提供了一种无锁的线程安全机制，用于解决并发编程中的同步问题。

这些原子类提供了一组操作方法，可以在多线程环境下对变量进行原子操作，保证操作的原子性和可见性。

下面是一些常用的原子类：

1. `AtomicInteger`：用于对整型变量进行原子操作，提供了类似于 `iAnt` 类型的操作方法，如增加、减少、获取当前值等。
2. `AtomicLong`：类似于 `AtomicInteger`，但用于对长整型变量进行原子操作。
3. `AtomicBoolean`：用于对布尔变量进行原子操作，提供了原子的更新方法。
4. `AtomicReference`：用于对引用类型变量进行原子操作，提供了原子的读取和设置方法。

```java
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicExample {
    private AtomicInteger count = new AtomicInteger(0);

    public void increment() {
        count.incrementAndGet();
    }

    public int getCount() {
        return count.get();
    }
}

```

### Volatile 变量修饰 

当一个线程对 `volatile` 变量进行写操作后，该值会立即被刷新到主内存中，并通知其他线程的工作内存中的缓存失效。其他线程在读取该变量时，会从主内存中重新获取最新值，确保读取到的是最新的值

-   volatile关键字为域变量的访问提供了一种免锁机制
-   使用volatile修饰域相当于告诉虚拟机该域可能会被其他线程更新
-   因此每次使用该域就要重新计算，而不是使用寄存器中的值 
-   volatile不会提供任何原子操作，它也不能用来修饰final类型的变量 

```java
public class VolatileExample {
    private volatile int count = 0;
    public void increment() {
        count++;
    }
    public int getCount() {
        return count;
    }
}
```

## 线程间通信


Java 中的线程通信是通过对象的等待（wait）和通知（notify/notifyAll）机制实现的。这种机制允许线程在某个条件满足或特定事件发生之前等待，并在条件满足或事件发生时被其他线程进行通知。

线程通信的基本原理如下：

1. 等待（wait）：当一个线程在某个条件下无法继续执行时，可以调用对象的 `wait()` 方法使自己进入等待状态。在调用 `wait()` 方法时，线程会释放它所持有的对象锁，使其他线程有机会获取该锁并执行相应操作。线程将一直保持等待状态，直到其他线程调用相同对象上的 `notify()` 或 `notifyAll()` 方法来唤醒等待线程。
2. 通知（notify/notifyAll）：当某个条件满足或特定事件发生时，线程可以调用对象的 `notify()` 或 `notifyAll()` 方法来唤醒等待状态的线程。调用 `notify()` 方法会随机选择一个等待线程进行唤醒，而调用 `notifyAll()` 方法会唤醒所有等待线程。被唤醒的线程将从等待状态转为可运行状态，然后通过竞争对象的锁来获得执行权。
3. 锁定机制：线程通信通常基于某个共享对象进行，而共享对象的访问需要加锁以保证线程安全。等待和通知的操作都需要在同步代码块或同步方法中使用，以确保线程间的通信与同步操作的原子性。只有持有对象的锁的线程才能执行 `wait()`、`notify()` 或 `notifyAll()` 方法。

```java
import java.util.LinkedList;

public class ProducerConsumerExample {
    private LinkedList<Integer> buffer = new LinkedList<>();
    private final int BUFFER_SIZE = 5;

    public void produce() throws InterruptedException {
        synchronized (this) {
            while (buffer.size() == BUFFER_SIZE) {
                wait(); // 缓冲区已满，生产者线程等待
            }
            int item = produceItem();
            buffer.add(item);
            System.out.println("Producer produced: " + item);
            notify(); // 唤醒一个等待的消费者线程
        }
    }

    public void consume() throws InterruptedException {
        synchronized (this) {
            while (buffer.isEmpty()) {
                wait(); // 缓冲区为空，消费者线程等待
            }
            int item = buffer.remove();
            System.out.println("Consumer consumed: " + item);
            notify(); // 唤醒一个等待的生产者线程
        }
    }

    private int produceItem() {
        // 生产物品的逻辑
        return 1; // 简化示例，返回固定值
    }
}

```

