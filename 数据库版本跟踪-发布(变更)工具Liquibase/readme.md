# demo基于SpringLiquibase + Srping web + H2 database
- Liquibase的好处：
1. 非常配合spring boot的一次编译多环境运行的性质
2. 版本管理，持续集成，发布时再也不用担心我整理了sql脚本了没
3. 支持跨数据库执行,例如demo里是h2,测试，正式环境是mysql也完全没有问题

- 要注意的：
1. 数据库账号是否有更新表结构的权限要注意。
2. 多人合作要有个changeSet文件结构的简单规范（按业务分，按时间分）

- doc参考这里： https://docs.liquibase.com/change-types/community/add-primary-key.html

