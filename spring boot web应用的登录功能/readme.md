# 登录该有的功能
- 登录其实和注册是相关联的功能，但是，搞通了登录功能，注册也是差不多原理的，常规检验一个登录功能会有以下几步:
- 账号密码的字符编码，长度，至少满足能保存到数据库里
- 密码要加密保存，加密
- 密码加密不能是简单的md5散列，md5散列可能被有心人通过md5 明文数据库轻松匹配破解，推荐org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
- 如何证明是人类发起的登录操作（防止暴力破解密码）

# 登录的本质
- http协议本身是没有状态的，登录的意义就是让客户端与服务端通讯时知道竟然是谁在发送请求
- session cookie机制，服务端通过session保存用户的资料，客户端通过cookie存储服务端session的索引
- jwt机制,服务端通过加密算出来的一个token,token 的playload记录了客户的非敏感信息，客户端自行选择存储方案，服务器通过验证客户端jwtToken签名来确定是否合法token
