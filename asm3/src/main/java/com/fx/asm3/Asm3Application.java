package com.fx.asm3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.fx.asm3")
public class Asm3Application {

	public static void main(String[] args) {
		SpringApplication.run(Asm3Application.class, args);
	}

}
