项目模板介绍
- demo项目里主要使用spring security + spring session
- 默认关闭csrf,方便再postman访问
- 项目运行需要启动一个localhost:6379的redis-server
- session保存在redis里，让项目可以多进程部署运行
- cookie + session的模式比较适合纯html应用，小程序，手机apps等会比较麻烦，没有原生cookie对象，可以通过第三方库实现，但就麻烦到前端了。
- cookie + session的模式做单点登录还需要一个cas服务，增大了开发维护的成本