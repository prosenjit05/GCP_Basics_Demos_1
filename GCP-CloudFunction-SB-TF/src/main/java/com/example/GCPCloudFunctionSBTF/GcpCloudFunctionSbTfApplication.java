package com.example.GCPCloudFunctionSBTF;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GcpCloudFunctionSbTfApplication {

	public static void main(String[] args) {
		SpringApplication.run(GcpCloudFunctionSbTfApplication.class, args);
	}

	public static String helloWorld() {
		return "Hello, World!";
	}
}
