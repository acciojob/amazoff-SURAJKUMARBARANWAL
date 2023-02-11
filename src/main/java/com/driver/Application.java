package com.driver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@RequestMapping("orders")
@EnableSwagger2
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}
}
