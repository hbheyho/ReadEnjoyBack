# 项目介绍
ReadEnjoy为公版书共享网站实战项目,用户可以在上面分享自己所收藏的公版书书籍、下载他人书籍、在线查看书籍以及评论等等。 纯粹为个人兴趣而发起,旨在熟悉前后端分离技术, 以及加强对SSM框架的理解, 还存在很多不足之前, 敬请谅解。该项目分为三个仓库, ReadEnjoyFront、ReadEnjoyAdmin和ReadEnjoyBack。

#### 仓库介绍
ReadEnjoyBack仓库为公版书共享网站的后端实现,采用的是SSM(Spring、SpringMVC、Mybatis)框架, 主要用来处理前端请求, 然后分发到不同的Controller进行处理, 只进行请求处理和数据返回, 做到与前端分离。
#### 版本说明
- Spring 4.0.0.RELEASE
- SpringMVC 1.3.0
- Mybatis 3.4.1
- Tomcat 7.0.64
- JDK 1.8.0
#### 使用说明
- 把项目拉到本地后,使用Maven下载依赖的Jar包
- 配置好Tomcat和JDK之后便可运行
- ps: 若想要实现书籍在线浏览, 还需要下载Openoffice软件并窗口启动便可
