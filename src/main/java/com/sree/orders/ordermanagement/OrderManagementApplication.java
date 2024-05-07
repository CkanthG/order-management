package com.sree.orders.ordermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.sree.orders.ordermanagement.config.Auth0Config;

@SpringBootApplication
@EnableConfigurationProperties(Auth0Config.class)
public class OrderManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderManagementApplication.class, args);
	}

}
