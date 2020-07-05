# demo包含 spring data redis spring web  
- 后端原理就是增对某些需要限制的请求地址 /abc/abc 进行拦截,判断是否拥有formId的header的,有就在redis里匹配
- 前端就是能生成一个唯一不重复的id放到http header里就行了
- 为了前端能配合写也实现了自定义的异常和对应的处理器FromSubmitException CustomExceptionHandler

- 现在前端要处理就相对简单了:
1. js 生成唯一id
```
function getGuidFormId() {  
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {  
        var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);  
        return v.toString(16);  
    });  
}
```
2. disbale提交按钮
3. 等到异步请求complete了页面的状态改好后再考虑刷新formId
