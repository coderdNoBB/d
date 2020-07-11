# spring cloud 
- spring cloud有点像2010年ssh的时候那样,烂大街都在要求会,无论新项目,重构我们都要使用它,甚是盲目
- spring cloud的优点主要是增加了应用的性能和扩展性和规划性
```
1. 可以使用多种nosql来加速你的业务请求
2. 发布功能不再stop the world了
3. 分布式开发思维可否继续@transactional
4. spring boot在少业务|功能的小team里比cloud更好
5. spring cloud一定开启code review一个代码bug能摧毁一切
```
- spring cloud更偏重可用性,容错性,从原配java注册中心eureka分析就能得知
- spring cloud开发注意点:
- 超时： 
```
ribbon:
1. 一般服务走走rdb nosql超时设置200ms就行了
2. 你一定要用srping boot来做上传大文件或者你一定要用mysql来聚合大量数量的话单独对xxxxSevice设置个几分钟,那要连feign的default timeout也改,天啊,然后你发现nginx的也要改
feign:
1. 不要做设置,让ribbon,hystrix去使用它
hystrix: 
1. timeout在代码里绝对大过ribbon timeout,让ribbon有时间进行retry 
```
- 分布式事务
```
1. 把强事务性的逻辑合并到一个服务里,我认为是最好的方法,但我有一次这样面试的这样回答被否定了,但他没告诉我他是怎么做的
2. 自己写些守护线程去让数据达到最终一致性
3. 用一些中间件GTS,Seata,接下来会写demo去测试一下
```
- 日志采集elk
```
1. Elasticsearch、Logstash、Kibana 解决分布式,集群下日志好难定位查找的问题
2. 偏运维的活
3. 弄之前考虑如何兼容你的容器编排工具
4. 建议采购云计算商业服务
```
- 容器调度
```
1. 本质上解决的是N个spring boot project的complie deploy monitor container service orchestration
2. 一般常用Kubernetes这个工具,没有一年的运维开发经验是hold不住的
3. 正常team应该选择采购云计算商业服务
```

