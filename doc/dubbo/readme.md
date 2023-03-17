## Dubbo

### 1.dubbo支持的协议

- dubbo
- rmi
- hession
- http
- webservice
- thrift
- redis
- rest

### 2.dubbo支持的序列化方式
- Hession2
- json
- jdk原生
- protobuf

### 3. dubbo容错机制
- Failover Cluster 重试
- Failfast Cluster 快速失败
- Failsafe Cluster 失败安全，出现异常，直接忽略。
- Forking Cluster 并行调用多个服务器，有一个成功就返回。
- Broadcast Cluster 广播调用所有提供者，逐个调用，任意一台报错则报错。
