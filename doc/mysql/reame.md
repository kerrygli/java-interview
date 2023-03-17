## Mysql


### 1.存储引擎为InnoDB，一条更新sql执行过程

- 客户端向Mysql发送执行update的命令；
- 删除t表的所有缓存（如果开启缓存的话）；
- 执行器调用InnoDB存储引擎的查询接口，先从Buffer pool中查询，如果不存在直接返回，
否则从磁盘中查询（如果id为主键，就会在聚簇索引上查询数据）；
- 在对查询到的记录修改前会先把旧值写undo page(undo log的缓存)；
- 执行器查询到记录后，调用InnoDB的接口把新值写入到Buffer pool中到data page,Mysql执行
增删改查，都是直接操作Buffer Pool,查数据都是先从Buffer Pool中查，修改数据时先写入Buffer pool;
- 把对Buffer pool中data page和undo page的修改记录到Log Buffer（redo log的缓存）中。至于Log Buffer中
内容何时持久化到磁盘，有不同的策略；
- 为了提升性能，事务执行过程中会把update操作记录到binlog cache中，具体的binlogcache什么时候刷盘，也可以
设置不同的策略；
- 客户端向mysql发起提交事务；
- 根据binlog刷盘策略把binlog cache刷盘到binlog文件中；
- 调用InnoDB存储引擎提交事务接口，修改redo log状态为commit,此时整个事务完成。

    redo log（重做日志）让InnoDB存储引擎拥有了崩溃恢复能力。binlog（归档日志）保证了MySQL集群架构的数据一致性。
虽然它们都属于持久化的保证，但是则重点不同

### 什么是MVCC ？
多版本控制：在读已提交，可重复读这两种隔离级别下事务在执行普通的select操作时访问记录读版本链的过程，可以使不同事务的读写，写读并发
操作，从而提高系统并发能力，这两个隔离级别有一个很大的区别就是生成readView的时机不同，读已提交是每次查询都会生成一个readView,可重复读
只是在第一次查询时生成一个，之后读查询都可以重复使用这个readView。

### Mysql加锁过程
唯一索引等值查询：
- 当查询记录存在的时候，next-key lock会退化成记录锁；
- 当查询的记录不存在时，next-key lock会退化成间隙锁；

非唯一索引：
- 当查询记录存在的时，除了会加next-key锁，还会加间隙锁；
- 当查询记录不存在时，只会加next-key lock，然后退化成间隙锁

唯一索引和主键索引范围查找加锁规则：
- 唯一索引在满足一些条件的时候，next-key lock退化成间隙锁或记录锁；
- 非唯一索引，next-key lock不会退化成间隙锁和记录锁。


Java中二叉树，红黑树，B+树
- 二叉树：
   左子树上所有节点的值小于它的根节点的值；
   右子树上所有节点的值均大于它的根节点的值；
   左右子树分别为二叉排序树，但特殊情况下可能形成一个有序数组形成的二叉树。
- 平衡二叉查找树
   在二叉树的基础上，要求两个子树的高度差不能超过1；
   每次增删都会通过一次或多次旋转来平衡二叉树；
- 红黑树
    一种自平衡二叉查找树，能够以O(log2N)的时间复杂度进行搜索，
插入，删除等操作。任何不平衡的都会在3次旋转以内解决。
 
- B树
   主要用于文件系统以及部分数据库索引，比如MongoDB,还有Mysql。

- B+树
   优势：IO一次读数据是从磁盘上读的，磁盘容量是固定的，取数据量大小事固定，
非叶子节点不存储数据，节点小，磁盘IO次数就少。
   B+树查询性能稳定，因为最终都是查找到叶子节点；
   叶子节点通过指针相连，更方便继续范围查找。
- 红黑树与B+树对比
   当数据量小的时候，红黑树可以把数据完全放在内存中，如linux中进程
调度用的就是红黑树，但是当数据量大时，不能一次性将数据加载到内存，红黑树
往往由于树的深度过大而造成磁盘IO读写过于频繁。

- Mysql深度分页优化
1. 记录每次取出的最大id,然后where id > 最大chua id
每次查询把本批次数据的最大id传给前端，查询下一页的时候再带到后台；
2. 通过in获取id
select * from tableName where id in (select id from tableName
 where userId = xxx limit 10000,10)
步骤：先根据where条件分页查出一页id,然后外层查询只需要回表查询返回这一页的数据行，
大大降低了无效的数据回表次数。

- 删除相同的数据，然后保留一条
delete from tableName
where name in (select Name from table group by Name having count(Name)>1)
and id not in (select min(id) from tableName group by Name having count(Name)>1)














