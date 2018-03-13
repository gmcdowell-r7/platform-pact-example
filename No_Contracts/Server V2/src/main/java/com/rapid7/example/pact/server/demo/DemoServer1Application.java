package com.rapid7.example.pact.server.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource({ "classpath:application.properties", "classpath:conf/config.properties" })
public class DemoServer1Application {

	public static void main(String[] args) {
		SpringApplication.run(DemoServer1Application.class, args);
	}
}
