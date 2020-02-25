package com.example.organizationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableEurekaClient
@EnableResourceServer  //is used to tell your microservice it’s a protected resource
public class OrganizationApp {
    public static void main(String[] args) {
        SpringApplication.run(OrganizationApp.class, args);
    }
}
