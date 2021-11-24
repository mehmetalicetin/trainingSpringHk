package com.cetin.education.spring.datapostgresql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class DataPostgresqlDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DataPostgresqlDemoApplication.class, args);
    }
}
