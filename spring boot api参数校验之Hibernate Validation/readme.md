# demo 基于spring web Hibernate Validation lombok 
- 震惊地发现现在还有很多人用spring web写接口时参数处理还是原始的，甚至有使用Map去接收的,对参数值合法行的校验是一个一个if套着的。
- Hibernate Validation的好处,让接口开发更加地面向对象,解耦了参数判断和curd的业务逻辑，甚至它还避免了xss,一举多得啊
- 注意：在CustomExceptionHandler处理后返回才看得到哦
