package com.contactsmanager.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.contactsmanager.*")
@SpringBootApplication
public class CMRESTFulWebAPIApp {

	public static void main(String[] args) {
		SpringApplication.run(CMRESTFulWebAPIApp.class, args);
	}
}
