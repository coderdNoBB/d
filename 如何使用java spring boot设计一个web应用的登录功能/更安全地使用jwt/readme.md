项目模板介绍
- demo项目里主要使用spring security + jsonwebtoken + redis
- 为了解决jwt可以被盗用的问题,加入了refreshToekn机制,并且缩短jwt的有效期
- 前端可以通过判断Expired JWT token.通过refreshToekn请求重新换取新的jwt,再次发起业务请求,这个前端需要对请求方法进行一定的封装
- api配置上https only的反问方式
- 现在只有熟知程序和secret的人能轻松地盗用你的token了,就算盗用了,把redis里的refreshToekn清掉也可以轻松让token失效了