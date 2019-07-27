package com.example.springboot_01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

//主程序
// @ImportResource(locations = {"classpath:beans.xml"})
@SpringBootApplication
public class Springboot01Application {

	public static void main(String[] args) {

		//启动spring应用
		SpringApplication.run(Springboot01Application.class, args);
	}

}
