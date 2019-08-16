package com.group1.fimnew;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.group1.controller"})
public class FimnewApplication {

	public static void main(String[] args) {
		SpringApplication.run(FimnewApplication.class, args);
	}

}
