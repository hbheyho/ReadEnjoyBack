### 项目介绍
ReadEnjoy为公版书共享网站实战项目,用户可以在上面分享自己所收藏的公版书书籍、下载他人书籍、在线查看书籍以及评论等等。<br> 纯粹为个人兴趣而发起,旨在熟悉前后端分离技术, 以及加强对SSM框架的理解, 还存在很多不足之前, 敬请谅解。该项目分为三个仓库, ReadEnjoyFront、ReadEnjoyAdmin和ReadEnjoyBack。

### 仓库介绍
ReadEnjoyBack仓库为公版书共享网站的后端实现,采用的是SSM(Spring、SpringMVC、Mybatis)框架, 主要用来处理前端请求, 然后分发到不同的Controller进行处理, 只进行请求处理和数据返回, 做到与前端分离。
### 版本说明
- Spring 4.0.0
- SpringMVC 1.3.0
- Mybatis 3.4.1
- Tomcat 7.0.64
- JDK 8
- 其中jar包版本详见pom文件
- Nginx 1.16.1
### 资源说明
- 数据库执行文件dbreadenjoy.sql存放在others文件夹中
- 书籍文件和图片存储在FTP服务器中,使用Ngnix进行代理,Nginx配置文件在others/nginxConf文件夹中
### 使用说明
- 把项目拉到本地后,使用Maven下载依赖的Jar包
- 配置好Tomcat和JDK
- 把datasource.properties改为自身的数据库配置信息
- 修改logback.xml中的日志路径信息
- 修改readenjoy.properties中的FTP服务器信息和个人邮件信息
- 书籍内容在线浏览依赖于把PDF文件转换为SWF格式文件,要实现正确格式转换,还需要下载Openoffice软件并窗口启动便可,参考：[Java实现office文档与pdf文档的在线预览功能](https://www.cnblogs.com/liaoweipeng/p/4767660.html)<br>

**悄咪咪推荐个人Blog:** [HB's Blog](http://www.huangbin.fun)
