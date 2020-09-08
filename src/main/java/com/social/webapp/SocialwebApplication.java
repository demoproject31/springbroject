package com.social.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication(scanBasePackages = {"com.social.webapp.controller","com.social.webapp.repocitory","com.social.webapp.service","com.social.webapp.model","com.social.webapp.Others","com.social.webapp.security"})
@EntityScan(basePackages = {"com.social.webapp.entity"})
public class SocialwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialwebApplication.class, args);
	}

}
