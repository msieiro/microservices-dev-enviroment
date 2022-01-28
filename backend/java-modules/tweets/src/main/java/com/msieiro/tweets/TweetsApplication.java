package com.msieiro.tweets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.msieiro.clients")
public class TweetsApplication {
    public static void main(String[] args) {
        SpringApplication.run(TweetsApplication.class, args);
    }
}
