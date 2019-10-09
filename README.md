## WxJava - 微信开发 Java SDK（开发工具包） 

> 本项目仅是一个SDK开发工具包，未提供Web实现，建议使用 maven 或 gradle 引用本项目即可使用本SDK提供的各种功能，详情可参考 [【Demo项目】][1] 或本项目中的部分单元测试代码；
>
>另外微信开发新手请务必阅读[【开发文档 Wiki 首页】][2]的常见问题部分，可以少走很多弯路，节省不少时间。

各个模块的Javadoc可以在线查看：
---
* [weixin-java-miniapp][3]
* [weixin-java-pay][4]
* [weixin-java-mp][5]
* [weixin-java-common][6]
* [weixin-java-cp][7]
* [weixin-java-open][8]

Maven 引用方式
---

注意：最新版本（包括测试版）为 Maven Central，以下为最新正式版。

```
<dependency>
  <groupId>com.github.binarywang</groupId>
  <artifactId>（不同模块参考下文）</artifactId>
  <version>3.5.0</version>
</dependency>
```
* 微信小程序：weixin-java-miniapp
* 微信支付：weixin-java-pay
* 微信开放平台：weixin-java-open
* 公众号（包括订阅号和服务号）：weixin-java-mp
* 企业号/企业微信：weixin-java-cp

[1]:demo.md
[2]:https://github.com/Wechat-Group/WxJava/wiki
[3]:http://binary.ac.cn/weixin-java-miniapp-javadoc/
[4]:http://binary.ac.cn/weixin-java-pay-javadoc/
[5]:http://binary.ac.cn/weixin-java-mp-javadoc/
[6]:http://binary.ac.cn/weixin-java-common-javadoc/
[7]:http://binary.ac.cn/weixin-java-cp-javadoc/
[8]:http://binary.ac.cn/weixin-java-open-javadoc/
