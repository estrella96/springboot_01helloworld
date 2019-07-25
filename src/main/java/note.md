# hello world 项目创建
- 创建一个maven工程
- 导入spring boot依赖 idea自动导入
- 编写一个主程序 启动spring boot (Springboot01Application)
- 编写相关的Controller Service
- 运行主程序
- 部署
这个插件可以将应用打包成可执行的jar包 直接部署 
Maven Projects Lifecycle package 
java -jar 打包的jar包 运行
 <build>
   		<plugins>
   			<plugin>
   				<groupId>org.springframework.boot</groupId>
   				<artifactId>spring-boot-maven-plugin</artifactId>
   			</plugin>
   		</plugins>
   	</build>
# 原理
## POM 文件
### 父项目

<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.1.6.RELEASE</version>
		<relativePath/> <!-- lookup parent from repository -->
</parent>
他的父项目
    <parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-dependencies</artifactId>
    <version>2.1.6.RELEASE</version>
    <relativePath>../../spring-boot-dependencies</relativePath>
    </parent>
 版本仲裁中心

### 导入的依赖
<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
</dependency>
spring-boot-starter:spring-boot场景启动器 导入了web模块正常进行依靠的组件


Spring boot 将所有的功能抽取出来做成了一个个starters 
导入不同场景的starter需要的dependency

## 主程序类 Springboot01Application
@SpringBootApplication：说明下面的类是主配置类 SpringBOOT 会运行这个类的main方法来启动应用
@SpringBootConfiguration：配置类（配置文件） 也是容器中的一个组件
@EnableAutoConfiguration: 开启自动配置 将主配置类SpringBootApplication的所在包及下面的子包里面所有的组件扫描到spring容器

# resources
##static
    静态资源 js css images
## templates
    模板页面 不支持jsp
    使用模板引擎 thymeleaf freemarker
## application.properties
    应用配置文件 server.port=8081 可以修改一些默认设置
    
