package com.example.zuulserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @EnableZuulServer - this annotation will create a Zuul Server that doesn’t load any of the Zuul reverse
 * proxy filters or use Netflix Eureka for service discovery.
 * @EnableZuulServer is used  when you want to build your own routing service and not use any Zuul prebuilt
 * capabilities. An example of this would be if you wanted to use Zuul to
 * integrate with a service discovery engine other than Eureka (for example, Consul).
 * @EnableZuulProxy - The Zuul Proxy server is designed by default to work on the Spring products.
 * Zuul will automatically use Eureka to look up services by their service IDs and then use
 * Netflix Ribbon to do client-side load balancing of requests from within Zuul.
 */

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class ZuulServerApp {
    public static void main(String[] args) {
        SpringApplication.run(ZuulServerApp.class, args);
    }
}
