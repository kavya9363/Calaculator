package com.example.calci;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CalciApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalciApplication.class, args);
		System.out.println("Done");
	}

}
