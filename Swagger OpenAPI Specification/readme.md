# swagger spring demo with jwt authorization
- swagger 是OpenAPI Specification的一个实现
- swagger 的优点：
1. spring boot的interface doc的好伴侣
2. 代替了代码注释,注释即文档
3. 符合面向对象的设计,跟随代码版本同步
4. 可以导入大众喜爱的http工具postman,方便前端调试

- 注意:
1. 如果要导入postman,最好加一个头部描述，这样import后可以更好地适配postman的环境变量,我暂时知道这个方法- - 
```
@ApiImplicitParams({
	@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header") 
})
```
