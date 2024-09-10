package com.example.supermercadospb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "com.example.supermercadospb")
@EntityScan(basePackages = "com.example.supermercadospb.entity")
public class SupermercadoSpbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupermercadoSpbApplication.class, args);
	}

}
