# demo 基于spring web + lombok 
- 要说应用服务器的上传下载,基本只限一个场景的,就是批量导入数据,或者导出报表之类的,文件只会在服务器临时存储一会的
- 如果你要长时间储存文件是不应该使用应用服务器,浪费带宽,多机负载时文件不知道存去哪里了
- 长时间储存文件应该使用对象存储,各种云计算平台都有,例如阿里云的oss,让前端申请一个ticket直接上传到oss里,节省应用服务器的带宽
- 上传处理完毕记得删除了临时的文件
- 如果导出的数据和列表一致,那其实应该让前端去做导出的功能,前端要有后端思维,后端的开发成本太高了,嘻嘻



