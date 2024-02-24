package com.smartflow.smartflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SmartFlowApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartFlowApplication.class, args);
	}

}
