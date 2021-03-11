package com.fsecure.webmonitioring;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@ComponentScan(basePackages={
        "com.fsecure.bootstrap",
        "com.fsecure.config",
        "com.fsecure.controllers",
        "com.fsecure.models",
        "com.fsecure.services"
    })
@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class})
public class WebmonitioringApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebmonitioringApplication.class, args);
	}

}
