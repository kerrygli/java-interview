## Java 集合

### 1.HashMap和ConcurrentHashMap

- HashMap

    1.7版本：底层采用Entry的链表数组，Entry是HashMap的
基本单元，每个Entry包含一个key-value键值对和一个指向下一
个Entry的引用next。

    1.8版本：引入了红黑树的实现，当链表长度大于8，数组长度大于16，
转换为红黑树结构。



- ConcurrentHashMap

  1.7版本：采用分段锁技术，底层采用segment的数组形式，一个segment数组
对应一个HashEntry数组，并且继承了ReetrantLock，所以segment是一种可
重入锁。
  整个segment[]数组和segment[0]不是惰性初始化，而是一个开始就创建，其他
segment对象则是要用到时才创建。


- 1.8版本：摒弃了segment分段锁的概念，直接采用transient volatile Node<K,V>[] table
保存，利用cas算法，采用table数组元素作为锁，从而实现了对每一行数据进行加锁，进一步减少并发
冲突的概率。

总结：
- get操作不加锁，原因是它的get方法里面将要使用的共享变量都定义成volatile类型。
get流程：
  1.根据key计算hash值，找到数组对应的index;
  2.如果该index位置无元素则直接返回null;
  3.如果该index位置有元素
  如果第一个元素的Hash值小于0，则该节点可能为ForwardNode(-1)或红黑树节点TreeBin;
如果是ForwardingNode(表示当前正在扩容，且已经扩容完成)，使用新的数组来排序；
如果是红黑树节点TreeBin,使用红黑树的查找方式来进行查找；
如果第一个元素Hash大于0,则为链表结构，依次遍历即可找到对应到元素，也就是读到时候不阻塞，
同时put也不会阻塞，读不加锁是因为使用来volatile修饰Node(元素value和指针next).

put流程：
  1.判断put进来的key和value是否为null,如果为null抛出异常；
  2.随后进入没有判断条件的for循环，何时插入成功，何时进行退出；
  3.在无限循环中，如果table数组为空，则调用initTable，初始化table;
  4.若table不为空，先hashCode，再无符号右移16位异或，再(n-1)&hash，定位到table中到位置，如果
位置为空，则使用CAS将新的节点放到table中。
  5.如果该位置不为空，且节点为moved(即forward节点，哈希值-1)说明正在扩容，且该节点已经扩容完成，如
果还有剩余任务该线程执行helpTransfer方法，帮助其他线程完成扩容，如果已经没有剩余任务，则该线程可以直接
操作新数组nextTable进行put；
  6.如果该位置不为空，且该节点不是forward节点。对桶中的第一个节点进行加锁，对该桶进行遍历，桶中的节点的hash值与key
值与给定的hash值和key值相等，则根据标识选择是否进行更新操作，如果没有找到相等的节点，则直接新生一个节点并赋值为之前最
后一个节点的下一个节点；
  7.若binCount值达到红黑树转化的阈值，则将桶中的结构转化为红黑树存储，最后，增加binCount值，最后调用addCount方法，将
concurrentHashmap的size加1，调用size方法时会用这个值。






