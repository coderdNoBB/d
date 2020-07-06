# hiernate spring jpa demo 改变你的心
- hibernate由一名澳大利亚墨尔本一位名为Gavin King的程序员在2001年发明，持久化框架标准Java Persistent API是参考hibernate实现的
- iBATIS myBatis发明到现在都是为了简化jdbc to javabean的一个过程,现在有个叫mybaties plus的项目的确不错,他们都只能叫做一种工具或者框架
- 我写这些不是说不要用mybaties,再次强调mybaties plus使mybatis更加符合jpa了,挺喜欢的
- 而hibernate更是一种思想,它时刻都在告诉你,你操作的是对象,而不是某个数据库

# 对hibernate的误解
1. 复杂查询难处理
2. sql优化难
3. hibernate无法批量处理数据
4. hibernate to json lazy load无限月读
5. hibernate 每次都返回完整的对象,浪费性能我只需要其中某几个字段

# 使用hibernate做开发时要舍弃你在操作一个数据库的概念
1. 当你要保存一个数据时,去设计对象的属性,不要去做表设计
2. 不要有一对多的设计,open entity in view关闭它最好,否则转换城json时要处理无限月读问题
3. hibernate是拥有hql给大家解决多表查询的
4. hibernate通过cache机制增提升web应用性能
5. hibernate是能配合jdbc 进行 batch commit的 

# hibernate要注意的点
1. hibernate的数据库连接抽象后叫做session (不要用data jpa,自己去createSession感受一下整个流程,session是hibernate的核心)
2. hibernate 对象状态,和hibernate session有关系
```
临时态(Transient)：一句话理解： 刚new出来的实体对象,没有id,session里没有这个数据,数据库也没有这个数据
持久化状态(Persistent) 一句话理解: 通过查询或者更新后的状态,session里有这个数据,数据库里也有这个数据
分离状态{有些文章翻译为游离状态,我是理解不了,如何游如何离}(Detached) 一句话理解: 离开了session的对象,等待被jvm gc的对象
```
3. hibernate 的缓存机制
```
cache level 1 一句话理解：在同一个session里,save()对象后,对象变成持久化状态了,在session.flush()之前,你再次通过getId()获取这个对象,就能命中cache
cache level 2 一句话理解：level 2是跨session,先找level 1,再找level 2,需要自己开启,并且配置第三方的内存服务例如redis
```