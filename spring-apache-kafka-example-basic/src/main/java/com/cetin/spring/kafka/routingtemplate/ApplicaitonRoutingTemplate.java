package com.cetin.spring.kafka.routingtemplate;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.core.RoutingKafkaTemplate;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

@SpringBootApplication(scanBasePackages = "com.cetin.spring.kafka.repliyingkafkatemplate")
public class ApplicaitonRoutingTemplate {
    public static void main(String[] args) {
        SpringApplication.run(ApplicaitonRoutingTemplate.class, args);
    }

    @Bean
    public RoutingKafkaTemplate routingKafkaTemplate(GenericApplicationContext context, ProducerFactory<Object, Object> pf){
        //Clone the ProducerFactory with a different Serializer, register with Spring for shutdown.

        Map<String, Object> configs = new HashMap<>(pf.getConfigurationProperties());
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class);
        DefaultKafkaProducerFactory<Object, Object> bytesPF = new DefaultKafkaProducerFactory<>(configs);
        context.registerBean(DefaultKafkaProducerFactory.class, "bytesPF", bytesPF);

        Map<Pattern, ProducerFactory<Object, Object>> map = new LinkedHashMap<>();
        map.put(Pattern.compile("two"), bytesPF);
        map.put(Pattern.compile(".+"),pf); //Default PF with StringSerializer
        return new RoutingKafkaTemplate(map);
    }

    @Bean
    public ApplicationRunner runner(RoutingKafkaTemplate routingKafkaTemplate){
        return args -> {
            routingKafkaTemplate.send("one", "thing1");
            routingKafkaTemplate.send("two","thing2".getBytes());
        };
    }
}
