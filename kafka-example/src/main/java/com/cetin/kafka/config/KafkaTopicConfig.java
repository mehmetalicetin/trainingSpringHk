package com.cetin.kafka.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

/*
* To create a topic, we register a NewTopic bean for each topic to the application context.
* If the topic already exists, the bean will be ignored.
* We can make use of TopicBuilder to create these beans.
* KafkaAdmin also increases the number of partitions
* if it finds that an existing topic has fewer partitions than NewTopic.numPartitions.
* */

@Configuration
@EnableKafka
public class KafkaTopicConfig {

    @Value("${kafka.address}")
    private String bootsrapServers;

    @Value("${kafka.topic1}")
    private String topic1;


    @Value("${kafka.topic2}")
    private String topic2;

//    @Bean
//    public NewTopic topic1(){
//        return TopicBuilder.name(topic1).build();
//    }
//
//    @Bean
//    public NewTopic topic2(){
//        return TopicBuilder.name(topic2).build();
//    }

    /*
    * KafkaAdmin is responsible for creating new topics in our broker.
    * With Spring Boot, a KafkaAdmin bean is automatically registered.
    * */
    @Bean
    KafkaAdmin admin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootsrapServers);
        return new KafkaAdmin(configs);
    }
}
