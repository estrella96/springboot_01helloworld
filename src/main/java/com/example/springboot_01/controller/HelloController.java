package com.example.springboot_01.controller;


import org.springframework.stereotype.Controller;
import  org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller
//@ResponseBody
@RestController
public class HelloController
{

    @RequestMapping("/hello")
    public  String hello(){
        return "hello world";

    }
}
