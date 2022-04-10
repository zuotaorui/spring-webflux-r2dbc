package com.izx.r2dbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;


@SpringBootApplication
public class R2dbcIzxApplication {

    public static void main(String[] args) {
        SpringApplication.run(R2dbcIzxApplication.class, args);
    }

}
