package com.cetin.education.spring.restclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestClientExample {

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }


    public static void main(String[] args) {
        SpringApplication.run(RestClientExample.class, args);
    }
}
