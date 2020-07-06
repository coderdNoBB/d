# hiernate srping jpa demo
- hibernate由一名澳大利亚墨尔本一位名为Gavin King的程序员在2001年发明，持久化框架标准Java Persistent API是参考hibernate实现的
- iBATIS myBatis发现到现在都是为了简化jdbc to javabean的一个过程,现在有个叫mybaties plus的项目的确不错,他们都只能叫做一种工具或者框架
- 我写这些不是说不要用mybaties,再次强调mybaties plus使mybatis更加jpa了,挺喜欢的
- 而hibernate更是一种思想,它时刻都在告诉你,你操作的是对象,而不是某个数据库

# 对hibernate的误解
1. 复杂查询难处理
2. sql优化难
3. hibernate无法批量处理数据
4. hibernate to json lazy load无线月读
5. hibernate 每次都返回完整的对象,浪费性能我只需要其中某几个字段

# 使用hibernate做开发时要舍弃你在操作一个数据库的概念
1. 

# hibernate要注意的点
1. hibernate的数据库连接抽象后叫做session
2. hibernate 对象状态,和hibernate session有关系
```
临时态(Transient)：一句话理解： 刚new出来的实体对象,没有id,session里没有这个数据,数据库也没有这个数据
持久化状态(Persistent) 一句话理解: 通过查询或者更新后的状态,session里有这个数据,数据库里也有这个数据
分离状态{有些文章翻译为游离状态,我是理解不了,如何游如何离}(Detached) 一句话理解: 离开了session的对象,等待被jvm gc的对象

```
3. hibernate 的缓存机制
