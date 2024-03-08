package com.mehmetgenc.reviewservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ReviewServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(ReviewServiceApp.class, args);
    }
}