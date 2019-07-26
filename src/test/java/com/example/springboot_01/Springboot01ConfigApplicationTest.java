package com.example.springboot_01;

import com.example.springboot_01.bean.Person;
import com.example.springboot_01.bean.Person1;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


//单元测试 测试期间自动注入容器功能
@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot01ConfigApplicationTest {

    @Autowired
//    Person person;
    Person1 person;
    @Test
    public  void contextLoads(){
        System.out.println(person);

    }
}
