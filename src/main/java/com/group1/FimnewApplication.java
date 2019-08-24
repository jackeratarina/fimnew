package com.group1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@EnableAutoConfiguration
//@ComponentScan(basePackages = {"com.group1.controller"})
//@ComponentScan(basePackages = {"com.group1.dao"})
//@ComponentScan(basePackages = {"com.group1.entity"})

public class FimnewApplication {

	public static void main(String[] args) {
		SpringApplication.run(FimnewApplication.class, args);
	}

}
