package com.ucluverse.newucluverseserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class NewUcluverseServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewUcluverseServerApplication.class, args);
    }

}
