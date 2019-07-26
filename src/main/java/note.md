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
## 配置文件
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
    
   
    

    
