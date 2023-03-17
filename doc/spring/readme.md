## Spring

### 1.BeanFactory和ApplicationContext区别？

ApplicationContext是BeanFactory的子接口。

- BeanFactory采用的是延迟加载来注入Bean，当使用到某个bean的时候，才对该bean进行加载
实例化，这样不能发现存在的spring的配置问题。
  ApplicationContext则相反，是在容器启动的时候，一次性创建了所有的bean。这样可以尽早发现
配置中可能存在的问题。

- BeanFactory对于BeanPostProcessor和BeanFactoryPostProcessor
需要手动注册，而ApplicationContext需要自动注册。

- BeanFactory主要面对spring框架基础设施，面对spring自己。而ApplicationConext
主要面对spring使用的开发者。


### Spring事务失效的场景
- 事务方法访问修饰符没有声明public
- 方法自身调用问题（非事务方法insert()中调用自身类的事务方法insertUser()）;
- 事务传播机制设置的不正确；
  （事务传播机制他们规定了事务方法和事务方法发生嵌套调用时候事务如何进行传播）
- 多线程导致事务失败，两个方法在不同的线程中，获取到的数据库连接不一样，所以是两个
不同的事务。同一个事务，指的是同一个数据库连接。















