package com.example.zuulserver;

import com.example.zuulserver.util.UserContextInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

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

    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate() {
        RestTemplate template = new RestTemplate();
        List interceptors = template.getInterceptors();
        if (interceptors == null) {
            template.setInterceptors(Collections.singletonList(new UserContextInterceptor()));
        } else {
            interceptors.add(new UserContextInterceptor());
            template.setInterceptors(interceptors);
        }

        return template;
    }
}
