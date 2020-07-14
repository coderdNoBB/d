# eureka尤里卡
- eureka1.x是一个基于servlet实现的注册器,它每个server node都是equality的,通过好像session复制的方式同步服务状态
- zookeeper21世纪了还master slaver,这就是现在大家都开始使用spirng cloud的根本核心原因之一,追求公平公正
- 有少少网络编程知识都知道,通讯原理不外忽是第一次建立通讯,持续发送心跳包,注销通讯(主动,超时),毫无疑问eureka也是这样,正常javaer都能hold得住
- 使用eureka server的正确方式是,就是使用jhipster-registry,所有配置都帮你最优化了
- jhipster是一款最流行的spring ee手脚架,任何情景都可以使用它初始化构建
- eureka的特点:
1. 简单,开箱即用,容易维护
2. 偏向cap定理的ap 可用性,分区容错性,允许某个server node的cache data不是最新的
3. 完全基于java,其他客户端加入得实现eureka-client的全部功能,有开源项目也这样做了
4. 可以跨数据中心部署,默认以host domain方式通讯,也有人强行改成prefer ip address这种自断手臂的方式，这是不对的应该让dns去做dns的事
5. netfilx停止了eureka2的版本了开发了,没所谓,1.x也够用了,而且我们有jhipster-registry 
6. eureka1.x是目前最强的注册中心,并没有任何工具能战胜它
7. https://www.jhipster.tech/jhipster-registry/