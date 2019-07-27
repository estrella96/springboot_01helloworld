package com.example.springboot_01.config;

import com.example.springboot_01.service.HelloService;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//指明是一个配置类
@Configuration
public class MyAppConfig {
    @Bean
    //将方法的返回值添加到容器中 容器中这个组件默认的id是方法名
    public HelloService helloService(){
        System.out.println("配置类@Bean 给容器中添加组件");
        return new HelloService();

    }
}
