# 绪论

## 数据结构

### 逻辑结构

- 集合结构
- 线性结构
- 树结构
- 图结构或网状结构

## 存储结构

- 顺序存储
- 链式存储

## 算法

**算法** *是为了解决某类问题而规定的一个有限长的操作序列*

### 五个重要特性

- 有穷性
- 确定性
- 可行性
- 输入
- 输出

### 评判标准

- 正确性
- 可读性
- 健壮性
- 高效性

### 算法的复杂度

#### 时间复杂度

#### 空间复杂度



# 线性表

*多个数据特征相同的元素构成的有限序列称为* **线性表**

## 基本操作

- 初始化
- 取值
- 查找
- 插入
- 删除

## 顺序表



## 链式表

结构：数据域 + 指针域

### 常见分类

- 单向链表
- 循环链表
- 双向链表

## 顺序表与链表比较

### 空间性能

#### 空间分配

- 顺序表的存储空间必须预先分配，其元素扩充受限，容易造成空间浪费或者空间溢出的问题
- 链表不需要为其提前预备空间，只需要在使用时分配空间，当难以预估存储规模时应该使用链表作为存储结构

#### 存储密度

- 顺序表空间利用率为100%，每个空间都是为了存储数据
- 链表需要为指针域预留空间

### 时间性能

#### 存取效率

- 顺序表由数组实现，他是一种随机的存取结构，即在给定下标index后直接存取数据，效率高 O(1)
- 链表为顺序存储，在给定下标后需要从头开始依次遍历链表，直到找到下标数据 O(n)

#### 插入删除效率

- 顺序表在插入或删除数据时，一般来说都要移动线性表中的大半数据 O(n)
- 链表在插入或删除时只需要改变指针的指向即可， O(1)





# 栈和队列

**栈** *是限定仅在表尾进行插入或删除操作的线性表*



**队列** *是一种先进先出的线性表*





# 树

## 二叉树

### 完全二叉树

 ***对于其节点若左子树叶子节点最大层数是l，那么右子树的叶子节点最大层数是l或者 l+1***

最低层数m 与节点数量n 之间的关系 **m=log2(n)+1**



### 线索二叉树

### 遍历二叉树

#### 先序遍历

1. 访问根节点
2. 先序遍历左子树
3. 先序遍历右子树

#### 中序遍历

1. 中序遍历左子树
2. 访问根节点
3. 中序遍历右子树

#### 后序遍历

1. 后序遍历左子树
2. 后序遍历右子树
3. 访问根节点

### 哈夫曼树

又称 最优树，是一类带权路径长度最短的树

# 图

## 分类

- 无向图
- 有向图
- 完全图
- 连通图
- 强连通图
- 带权图
- 稀疏图
- 稠密图

## 存储方式

### 邻接矩阵

适用稠密图

### 邻接表

适用用稀疏图

# 查找

## 线性表的查找

- 顺序查找
- 二分查找
- 分块查找 *索引顺序查找*

## 树的查找

### 二叉排序树

定义

- 若左子树不为空，则左子树上所有的结点值小于它根节点的值
- 若右子树不为空，则右子树上所有的节点值大于它根节点的值
- 他的左右子树也为二叉排序树



### 平衡二叉树

又称AVL树

特征

- 左右子树的深度之差的绝对值不超过1
- 左右子树也是平衡二叉树

### B- 树

## 散列表的查找

基本思想：已知关键字集合 U，最大关键字为 m，设计一个函数 Hash，它以关键字为自变量， 关键字的存储地址为因变量，将关键字映射到一个有限的、地址连续的区间 T[0..n-1] (n<<m) 中，这个区间就称为散列表，散列查找中使用的转换函数称为散列函数。

### 散列表的构建

- 数字分析法
- 平方取中法
- 折叠法
- 除留 余数法

### 冲突解决

#### 开放地址法

- 线性探测
- 二次探测
- 伪随机探测

#### 链地址法

#### 再哈希法



# 排序

**排序** *是按照关键字的非递减或者递增顺序对一组记录重新进行排序操作*

![image-20230523112028179](image\image-20230523112028179.png)



## 插入排序

### 直接插入排序

```python

def insertSort(rl : list)-> list:
    for i in range(1,len(rl)):
        obj = rl[i]
        j = i - 1
        while obj < rl[j] and j >= 0:
            rl[j+1] = rl[j]
            j -= 1
        rl[j+1] = obj
    return rl

```



### 折半插入排序

### 希尔排序

```python
def shellSort(arr):
    """
    插入排序的改进版本，通过从大gap比较插入，大幅减小逆差和
    """
    n = len(arr)
    gap = n // 2  # 初始间隔设置为数组长度的一半
    while gap > 0:
        for i in range(gap, n):
            temp = arr[i]
            j = i
            while j >= gap and arr[j - gap] > temp:
                arr[j] = arr[j - gap]
                j -= gap
            arr[j] = temp
        gap //= 2  # 缩小间隔
    return arr
```



## 交换排序

### 冒泡排序

```python
def BubbleSort(rl: list) -> list:
    # 冒泡排序是交换排序的一种
    for i in range(len(rl)):
        sign = False
        for j in range(0,len(rl)-i-1):
            if rl[j] > rl[j+1]:
                rl[j],rl[j+1] = rl[j+1], rl[j]
                sign = True
        if sign is False:
            break
    return rl

```

### 快速排序

快速

```python
def quickSort(arr: list)-> list:
    if len(arr) <= 1:
        return arr
    
    temp = arr[len(arr) // 2]
    pre = [x for x in arr if x < temp]
    mid = [x for x in arr if x == temp]
    end = [x for x in arr if x > temp]

    return quickSort(pre) + mid + quickSort(end)

def quickSort2(arr=rl):
    """
    在原数组空间中交换
    """
    def partition(low,hight):
        key = arr[low]
        while low < hight:
            while low < hight and key <= arr[hight]: hight -= 1
            arr[low] = arr[hight]
            while low < hight and key >= arr[low]: low += 1
            arr[hight] = arr[low]
        arr[low] = key
        return low
    
    def Qsort(low,hight):
        if low < hight:
            mid = partition(low,hight)
            Qsort(low,mid-1)
            Qsort(mid+1,hight)

    Qsort(0,len(arr)-1)
    return arr
```



## 选择排序

在未排序部分选择最小（或最大）的元素，并将其放置在已排序部分的末尾

### 简单选择排序

```python
def selection_sort(arr):
    # 遍历整个数组
    for i in range(len(arr)):
        # 假设当前位置的元素是最小的
        min_index = i
        
        # 在未排序部分查找更小的元素
        for j in range(i + 1, len(arr)):
            if arr[j] < arr[min_index]:
                min_index = j
        
        # 将找到的最小元素与当前位置的元素交换
        arr[i], arr[min_index] = arr[min_index], arr[i]

```



### 树形选择排序

又称 锦标赛排序 堆排序

堆排序（Heap Sort）是一种基于堆数据结构的排序算法，它包括两个主要步骤：构建最大堆和反复从堆顶取出最大元素并调整堆

```python
def build_max_heap(arr):
    """
    构建最大堆
    """
    def max_heapify(arr, n, i):
        """
        将以i为根的子树调整为最大堆
        """
        largest = i  # 初始化最大元素的索引
        left_child = 2 * i + 1
        right_child = 2 * i + 2

        # 比较左子节点和根节点
        if left_child < n and arr[left_child] > arr[largest]:
            largest = left_child

        # 比较右子节点和当前最大节点
        if right_child < n and arr[right_child] > arr[largest]:
            largest = right_child

        # 如果最大节点不是根节点，交换它们，并继续调整子树
        if largest != i:
            arr[i], arr[largest] = arr[largest], arr[i]
            # 递归调整子树
            max_heapify(arr, n, largest)
    n = len(arr)
    # 从最后一个非叶子节点开始向上调整堆
    for i in range(n // 2 - 1, -1, -1):
        max_heapify(arr, n, i)
```





## 并归排序

归并排序（Merge Sort）是一种基于分治策略的排序算法，它的核心思想是将待排序的数组分成两个较小的子数组，然后分别对这两个子数组进行排序，最后将它们合并成一个有序的数组。归并排序是一种稳定的排序算法，具有良好的时间复杂度，通常在大型数据集上表现出色。

```python
def merge_sort(arr):
    if len(arr) > 1:
        mid = len(arr) // 2  # 找到数组中间位置
        left_half = arr[:mid]
        right_half = arr[mid:]

        # 递归地对左右子数组进行归并排序
        merge_sort(left_half)
        merge_sort(right_half)

        i = j = k = 0

        # 合并两个子数组
        while i < len(left_half) and j < len(right_half):
            if left_half[i] < right_half[j]:
                arr[k] = left_half[i]
                i += 1
            else:
                arr[k] = right_half[j]
                j += 1
            k += 1

        # 处理剩余的元素
        while i < len(left_half):
            arr[k] = left_half[i]
            i += 1
            k += 1

        while j < len(right_half):
            arr[k] = right_half[j]
            j += 1
            k += 1

```



## 基数排序

基数排序（Radix Sort）是一种非比较性的排序算法，它根据数字的每一位进行排序。它的工作原理是先从最低有效位（个位）开始，按照这一位的数值将元素分桶，然后依次处理更高位的数字，直到最高位排序完成。这个过程需要多轮迭代，直到所有位都被处理完。

```python
def radix_sort(arr):
    # 获取数组中最大元素的位数
    max_num = max(arr)
    max_digits = len(str(max_num))
    
    for digit in range(max_digits):
        # 创建10个桶（0到9）
        buckets = [[] for _ in range(10)]
        
        # 将元素按照当前位的数字分配到对应的桶中
        for num in arr:
            digit_val = (num // 10 ** digit) % 10
            buckets[digit_val].append(num)
        
        # 重新合并桶中的元素，按照当前位的顺序
        arr = []
        for bucket in buckets:
            arr.extend(bucket)
    
    return arr


```





## 外部排序

*前面讨论的排序都是内部排序，整个过程发生在内存中，当排序的记录数目很大，无法一次性调入内存时，排序过程就必须借助外存分批调入内存完成。*

### 多路并归





