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
```xml
 <build>
   		<plugins>
   			<plugin>
   				<groupId>org.springframework.boot</groupId>
   				<artifactId>spring-boot-maven-plugin</artifactId>
   			</plugin>
   		</plugins>
   	</build>

```

# 原理
## POM 文件
### 父项目
```xml
<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.1.6.RELEASE</version>
		<relativePath/> <!-- lookup parent from repository -->
</parent>
```

他的父项目
```xml
    <parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-dependencies</artifactId>
    <version>2.1.6.RELEASE</version>
    <relativePath>../../spring-boot-dependencies</relativePath>
    </parent>
```

 版本仲裁中心

### 导入的依赖
```xml
<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

spring-boot-starter:spring-boot场景启动器 导入了web模块正常进行依靠的组件


Spring boot 将所有的功能抽取出来做成了一个个starters 
导入不同场景的starter需要的dependency

## 主程序类 Springboot01Application
@SpringBootApplication：说明下面的类是主配置类 SpringBOOT 会运行这个类的main方法来启动应用
@SpringBootConfiguration：配置类（配置文件） 也是容器中的一个组件
@EnableAutoConfiguration: 开启自动配置 将主配置类SpringBootApplication的所在包及下面的子包里面所有的组件扫描到spring容器

## resources
### static
    静态资源 js css images
### templates
    模板页面 不支持jsp
    使用模板引擎 thymeleaf freemarker
### application.properties
    应用配置文件 server.port=8081 可以修改一些默认设置
    
# Spring boot 配置
## 配置文件 @ConfigurationProperties
### 全局配置文件 
- application.properties
- application.yml
    作用：修改spring boot自动配置的默认值
    YAML：一种标记语言 不是一个标记语言
        yaml以数据为中心
        ```
        server:
            port: 8081
        ```
## YAML语法
### 基本语法
- k:空格v 属性-值 大小写区分
- 缩进控制层级关系 左对齐的一列是同一层级
- 值的写法 
    - 字面量：普通的值 
        k: v 直接写 字符串不需要加单双引号
        双引号转义 字符的含义
        单引号不转义 直接输出字符
    - 对象、Map：
        k: v
        ```
        friends:
                     lastName: jane
                     age: 20
        friends: {lastName: jane,age: 20}
        ```
        
    - 数组、集合：
        用-表示数组中的一个元素
        ```
        pets:
            - cat
            - dog
            - pig
        pets: [cat,dog,pig]

        ```
## 配置方式
###@ConfigurationProperties Person.java
###@Value Person1.java
- Value("字面量/ ${key}从环境变量 配置文件中获取值 / #{Spell} spring表达式")
- 在类的每一个属性上面加@Value(" ")  
###区别

| 区别 | @ConfigurationProperties | @Value |
|-|-|-|
| 功能 | 批量注入配置文件中的属性 | 一个个指定 |
| 松散绑定 last_name lastName | 支持 | 不支持 |
| SPEL 表达式 | 不支持 | 支持 |
| JSR303 | 数据校验 @Validated | 支持 | 不支持 |

如果只是在某个业务逻辑中需要获取一下配置文件中的某项值 使用@Value
如果专门编写了一个javaBean来和配置文件进行映射 就直接使用@ConfigurationProperties

### @PropertySource  @ImportResource
- @PropertySource 加载指定位置的配置文件 
    person.properties
    @PropertySource(value={"classpath:person.properties"})
- @ImportResource 导入Spring的配置文件 让配置文件内容生效
    xml
    @ImportResource(locations = {"classpath:beans.xml"})

###Spring Boot 推荐给容器中加组件方式
- 全注解
- 1 配置类====配置文件 
    config.MyAppConfig.java
    @Configuration 指明当前类是一个配置类
- 2 使用@Bean给容器中添加组件
    ```java
    @Configuration
           public class MyAppConfig {
               @Bean
               //将方法的返回值添加到容器中 容器中这个组件默认的id是方法名
               public HelloService helloService(){
                   System.out.println("配置类@Bean 给容器中添加组件");
                   return new HelloService();
           
               }
           }

    ```
### 配置文件占位符
- RandomValuePropertySource配置文件可以使用随机数
    ${random.value} ${random.int} ${random.long}
    ${random.int(10)}
- 属性配置占位符
    可以在配置文件中引用前面配置过的属性
    ${app.name:默认值} 指定找不到属性时的默认值
    
## Profile 多环境配置
对不同环境提供不同配置功能的支持 通过激活 指定参数等方式快速切换环境
- 多profile文件形式
    - application-{profile}.properties:
      profile: dev(开发) prod（生产）
      指定激活 application.properties里面写：spring.profiles.active=dev
- yml支持多文档块  
    spring:
      profiles:
        active: dev
    ---
    server:
        port:8081
    spring:
        profiles:dev
    ---
- 激活指定profile
   - 配置文件中指定 spring.profiles.active=dev
   - 命令行
    --spring.profiles.active=dev
   - 虚拟机参数
    -Dspring.profiles.active=dev

## 配置文件加载位置
- 扫描以下位置的application.properties/application.yml作为默认配置文件
    - file:./config/
    - file./
    - classpath:/config/
    - classpath:/
    优先级从高到低 这四个位置都会加载 互补配置 同样的内容高覆盖低
    - spring.config.location 可以改变默认配置（运维时使用）
        项目打包好之后 使用命令行参数形式 启动项目的时候指定配置文件的新位置 
        指定配置文件和默认配置文件一起起作用 互补配置
## 外部配置加载顺序
优先级从高到低

    - Devtools global settings properties on your home directory (~/.spring-boot-devtools.properties when devtools is active).
      
    - @TestPropertySource annotations on your tests.
      
    - properties attribute on your tests. Available on @SpringBootTest and the test annotations for testing a particular slice of your application.
      
    - Command line arguments.命令行参数
      
    - Properties from SPRING_APPLICATION_JSON (inline JSON embedded in an environment variable or system property).
      
    - ServletConfig init parameters.
      
    - ServletContext init parameters.
      
    - JNDI attributes from java:comp/env. 
      
    - Java System properties (System.getProperties()).
      
    - OS environment variables.
      
    - A RandomValuePropertySource that has properties only in random.*.
      
    - Profile-specific application properties outside of your packaged jar (application-{profile}.properties and YAML variants). jar包外部的application-{profile}.properties and YAML 文件
      
    - Profile-specific application properties packaged inside your jar (application-{profile}.properties and YAML variants). jar包内的。。。。。
      
    - Application properties outside of your packaged jar (application.properties and YAML variants). jar包外不带spring.profile
      
    - Application properties packaged inside your jar (application.properties and YAML variants). jar包内不带。。。。
      
    - @PropertySource annotations on your @Configuration classes.
      
    - Default properties (specified by setting SpringApplication.setDefaultProperties).
      

     
    
        




  
  
  
    

    
