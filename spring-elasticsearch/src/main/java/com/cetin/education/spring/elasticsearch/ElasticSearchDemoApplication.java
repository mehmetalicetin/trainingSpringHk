package com.cetin.education.spring.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories
public class ElasticSearchDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ElasticSearchDemoApplication.class,args);
    }
}
