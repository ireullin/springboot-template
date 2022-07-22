package com.ireullin.springboottemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.oas.annotations.EnableOpenApi;

@Slf4j
@EnableOpenApi
@SpringBootApplication
public class SpringbootTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootTemplateApplication.class, args);
	}

}
