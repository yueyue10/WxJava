### 本Demo基于Spring Boot构建，实现微信公众号后端开发功能。
### 本项目为WxJava的Demo演示程序，更多Demo请[查阅此处](https://github.com/Wechat-Group/WxJava/blob/master/demo.md)。

原项目地址：https://github.com/binarywang/weixin-java-mp-demo-springboot

### 新实现的功能：
* swagger接口文档
    ![swagger-ui.png](https://github.com/yueyue10/WxJava/blob/master/images/mp-demo/swagger-ui.png?raw=true)
* 公众号获取用户身份信息
* 公众号网页jssdk各权限测试

#### 项目使用记录
* 微信公众号官网服务器相关配置[参考文档][0]
    * 此处需要对外的服务器地址，所以使用nat123进行
    ![nat123.png](https://github.com/yueyue10/WxJava/blob/master/images/mp-demo/nat123.png?raw=true)
    ![nat123_setting.png](https://github.com/yueyue10/WxJava/blob/master/images/mp-demo/nat123_setting.png?raw=true)
* 微信公众号测试账号[地址][1]
    ![test_account.png](https://github.com/yueyue10/WxJava/blob/master/images/mp-demo/test_account.png?raw=true)
    ![test_account_网页账号_配置.png](https://github.com/yueyue10/WxJava/blob/master/images/mp-demo/test_account_网页账号_配置.png?raw=true)
* 测试网页权限等使用微信开发者工具请求：http://58g0b94ec.nat123.cc/demo/index.html
    ![公众号网页权限测试.png](https://github.com/yueyue10/WxJava/blob/master/images/mp-demo/公众号网页权限测试.png?raw=true)
* 获取公众号用户信息：
    - 通过接口生成授权网页(带有回调接口地址)
        ![获取用户信息_1构造网页授权url.png](https://github.com/yueyue10/WxJava/blob/master/images/mp-demo/获取用户信息_1构造网页授权url.png?raw=true)
    - 微信用户访问授权网页并授权
        ![获取用户信息_2微信用户授权.png](https://github.com/yueyue10/WxJava/blob/master/images/mp-demo/获取用户信息_2微信用户授权.png?raw=true)
    - 授权成功自动访问回调接口地址并携带code参数，接口逻辑代码在WxRedirectController中
        ![获取用户信息_3用户信息展示.png](https://github.com/yueyue10/WxJava/blob/master/images/mp-demo/获取用户信息_3用户信息展示.png?raw=true)
        - 通过code获取accessToken，通过accessToken获取用户信息WxMpUser
        - 通过{templates}{greet_user}页面展示用户信息

#### 参考网址：
* [微信公众平台开发网页开发博客][10]
* [调用微信内置的方法及wx.config的配置问题][11]
* [微信JS-SDK Demo][12]
* [微信服务号 redirect_uri域名与后台配置不一致，错误代码10003][13]
##### 基础参考：
* [微信公众号开发系统入门教程][20]
* [安装nat123，将自己的电脑暴露在外网中][21]
* [微信公众号后台开发总结][22]
* [微微信开发中的token获取][23]
* [nat123内网网站发布到外网-五种方法][24]

[0]:https://developers.weixin.qq.com/doc/offiaccount/Basic_Information/Access_Overview.html
[1]:https://mp.weixin.qq.com/debug/cgi-bin/sandbox?t=sandbox/login

[10]:https://blog.csdn.net/weixin_41049850/article/details/81000050
[11]:https://www.cnblogs.com/gygtech/p/9173647.html
[12]:https://www.weixinsxy.com/jssdk/
[13]:https://www.cnblogs.com/yangzailu/p/8983837.html

[20]:https://blog.csdn.net/a1786223749/article/details/80787379
[21]:https://blog.csdn.net/xcc_2269861428/article/details/79253046
[22]:https://blog.csdn.net/wukuncsdn/article/details/79614318
[23]:https://www.cnblogs.com/gede/p/10927426.html
[24]:http://www.nat123.com/Pages_8_549.jsp
