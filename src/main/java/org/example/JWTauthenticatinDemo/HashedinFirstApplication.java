package org.example.JWTauthenticatinDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class HashedinFirstApplication {

    public static void main(String[] args) {
        SpringApplication.run(HashedinFirstApplication.class, args);
    }

}
