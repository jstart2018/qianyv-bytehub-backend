package com.qianyvbygtehub.auth;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.qianyvbytehub.auth")
@MapperScan("com.qianyvbytehub.**.dao")
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class);
    }
}
