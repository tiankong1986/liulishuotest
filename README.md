# liulishuotest


总体概括：
本project主要是实现了一个与用户金币交易相关的microservice，可以提供用户金币查询，用户金币添加以及用户之间的金币transfer等服务 

主要框架：
1、使用springMVC提供restful webservice
2、使用mybatis作为DAO层的基本框架
3、使用C3PO的数据连接池管理数据库连接的相关问题
4、利用spring boot 提供microservice运行部署环境
5、利用jvm提供的HttpServer相关资源，模拟了一个小的httpserver，并且与java management extentions API配合得到本java进程的jstack


主要环境配置：
jdk 1.6
mysql 5.6
maven 3.2.1
spring 4.0.X
spring boot 1.1.4release
其他请参考pom.xml


开发工具：
eclipse

代码结构：
1、coins-txn-conf主要提供一些配置信息，例如maven、项目打包等
2、coins-txn-mvn-import便于import项目
3、coins-txn-core提供核心业务服务
4、coins-txn-api-ws提供rest访问URL以及部署环境
5、db.sql相关的数据库脚本

注意事项：

把代码部署到自己的机器上后，可能需要修改如下配置：
1、修改maven setting（coins-txn-conf中的settings.coins.txn.ws.xml）例如，把仓库改成自己的等
2、修改自己的打包安装脚本（coins-txn-conf中的mvn build.bat）
3、修改自己的数据库配置（coins-txn-core中的app.properties）
4、目前提供的api服务是8080端口，jstack服务是8081端口，如果想修改api端口可以在coins-txn-api-ws中的application.properties配置，如果要修改jstack端口，需要查看代码（由于时间等因素，没有做成配置文件，接下来进行完善）
中待续（日志等）
