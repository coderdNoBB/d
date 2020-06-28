项目模板介绍
- demo项目里主要使用spring security + jsonwebtoken
- 展示了如何创建jwt和校验token签名的过程

- jwt也是springCloud模式下常用的接口鉴权方式
- jwt缺点一是无法销毁：泄漏出去的token,如果过期时间过长,无法被销毁,除非修改secret批量失效之
- jwt缺点二是token很长,比jsessionId长很多,如果有大量的http请求会增加流量等,一般放在http header里,而一般web serve 对 header处理应该是8K
- jwt缺点三,playload存放不了敏感信息,所以你最多存个用户的索引和权限的索引等,我看网上有说把playload的信息用同样的secret再加密一次,也是可以,但你将付出更多的cpu资源来解密,但同时也减少了查询数据库缓存等次数，看具体情况吧
- jwt优点一,多系统授权-鉴权,多个项目使用相同的secret去做token的解密验证签名步骤就可以了(直白点拷贝demo的代码到同样的项目里就行),适应不同的语言,php,工哦
- jwt优点二,开发维护轻松,性能好
- jwt有点三,更适合移动端的鉴权,移动端没有cookie
- jwt优点四,比较和session cookie的代码,会发现jwt的toekn create流程更加清晰方便