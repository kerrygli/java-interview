## JVM


### 1.常用jvm命令

- jps：查看本机Java进程信息
    jps -l
    jps -v
    ps -ef | grep ""
- jstack:打印线程的栈信息
    jstack pid >> stack.txt
    
- jmap：打印内存映射
     
    jmap -histo:live pid  # 打印存活对象的大小和个数
  
    jmap -dump:format=b,file=test.hprof pid
通过jconsole,jvisualvm
- jstat:查看堆内存使用情况和gc情况

    jstat -gcutil pid 100 10


### 2.tomcat为什么使用自定义类加载器？
因为一个tomcat可以部署多个应用，每个应用中可能部署多个应用，
可能存在全类名相同的类，tomcat启动之后就是一个java进程，也
就是一个jvm,并且只有一个类加载器，默认是AppClassLoader,而
tomcat为每个应用都生成一个类加载器webAppClassLoader,这样tomcat
中每个应用就可以使用自己都类加载器去加载自己都类，从而达到应用之间的类
隔离。


### 3.如何排查jvm相关问题
- 正常运行的系统
1. 通过jmap查看jvm各个区域的参数情况；
2. 通过jstack命令查看线程的运行情况，查看线程是否有阻塞，或者死锁；
3. 通过jstat 命令查看垃圾回收的情况，查看是否fullgc比较频烦；
4. 通过各个命令的结果，通过jvisualvm等工具进行分析。

- 已经发送了oom的系统
1. 配置发生oom时自动导出dump文件；
2. 利用jvisualvm分析dump文件；
3. 找到异常的对象，线程定位到具体的代码。

-XX:MaxGCPauseMillis 设置最大GC暂停时间，jvm将尽最大可能努力实现，主要通过调整young区的块数来实现。


### 4.对象从加载到jvm，再被gc清除，经历了那些阶段？
1. 首先将字节码文件加载到方法区；
2. 然后根据类信息在堆中创建对象；
3. 对象首先会分配在堆区年轻代中的eden区，进过一次Minor GC后，对象如果还存活，进入会Survivor区，
后续每次Minor GC，如果对象一直存活，就会在Suvivor区来回拷贝，每移动一次，年龄加1；
4. 当年龄超过15后，对象依然存活，就会进入老年代；
5. 如果经过full gc，被标记为垃圾对象，那么就会给gc 线程清理掉。

### 5. class.forName和classLoader加载不同点
类加载过程：
- 加载： 操作系统加载编译之后的class文件。
- 链接： 验证，准备，符号引用改为直接引用。
- 初始化：在虚拟机中根据class文件进行初始化。
- 使用
- 卸载：使用完了，java虚拟机进行清理。


classloader遵循双亲委派机制最终调用启动类加载器的类加载器。
classLoader将class文件加载到jvm中，不会执行static中的内容，
只有在newInstance才会去执行static块。

class.forName除了类的class文件加载到jvm内存中之外，还会对类进行解析，执行类中的
static块，当然还可以执行是否执行静态块。

java中不能自定义Object类，因为当一个类进行加载的时候，会依次向上级类加载器进行委托，
直到到达最顶层的bootstrap系统类加载器。它属于系统类。


### 5.一个java对象占用多少个字节
一个java对象的存储结构，在hotspot虚拟机中，对象在内存中的存储布局分为
3块区域：对象头header,实例数据InstanceData和对齐填充padding。
在关闭压缩指针的情况下，Object默认会占用16个字节，16个字节正好是8的整数倍，因此不需要填充。












