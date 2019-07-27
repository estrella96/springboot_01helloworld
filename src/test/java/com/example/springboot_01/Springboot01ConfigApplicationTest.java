package com.example.springboot_01;

import com.example.springboot_01.bean.Person;
import com.example.springboot_01.bean.Person1;
import javafx.application.Application;
//import org.apache.catalina.core.ApplicationContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.context.ApplicationContext;


//单元测试 测试期间自动注入容器功能
@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot01ConfigApplicationTest {

    @Autowired
//    Person person;
    Person1 person;

    @Autowired
    ApplicationContext ioc;

    @Test
    public void testHelloService(){
       boolean rst = ioc.containsBean("helloService");
       System.out.println(rst);
    }


    @Test
    public  void contextLoads(){
        System.out.println(person);

    }
}
