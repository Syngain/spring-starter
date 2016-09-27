# Spring Boot

华龙网海数科技 技术团队 Spring起步项目

使用[Idea Community](https://www.jetbrains.com/idea/download/)进行开发，并安装：[Scala plugin](https://plugins.jetbrains.com/plugin/?id=1347)和[Lombok plugin](https://plugins.jetbrains.com/plugin/6317)。 

需要JDK 8支持，并请安装[jce8](http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html)

使用Idea直接打开项目目录，会自动判断gradle项目类型并导入相关依赖。（注：第一次下载可能会比较慢，请耐心等待，也许你可以先喝杯咖啡）

**数据库**

Spring Starter程序需要MySQL支持，安装方式以Ubuntu 16.04 举例（[下载地址](http://mirrors.aliyun.com/ubuntu-releases/16.04.1/)）：

```
sudo apt install mysql-server mysql-workbench
```

- 创建数据库：springstarter
- 创建账号/密码：devuser@Devpass.2016

mysql-workbench是一款官方出口的图形化数据库管理工具。

**编译运行**

```
mkdir -p ~/hualongdata/
cd ~/hualongdata/
git clone https://github.com/hualongdata/spring-starter.git
cd spring-starter
./gradlew -p hl-web-boot bootRun
```

**访问Spring Boot应用**

打开浏览器访问：[http://localhost:28080/beans](http://localhost:28080/beans)

也可通过命令行访问Spring Boot应用：

```
curl -v http://localhost:28080/beans
```

