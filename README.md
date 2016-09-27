# Spring Boot

华龙网海数科技 技术团队 Spring起步项目

使用[Idea Community](https://www.jetbrains.com/idea/download/)进行开发，并安装：[Scala plugin](https://plugins.jetbrains.com/plugin/?id=1347)和[Lombok plugin](https://plugins.jetbrains.com/plugin/6317)。 

需要JDK 8支持，并请安装[jce8](http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html)

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

