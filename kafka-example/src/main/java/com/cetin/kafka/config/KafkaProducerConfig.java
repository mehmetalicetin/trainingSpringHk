package com.cetin.kafka.config;

import com.cetin.kafka.model.User;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.core.RoutingKafkaTemplate;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

/*
* ProducerFactory is responsible for creating Kafka Producer instances.
* BOOTSTRAP_SERVERS_CONFIG - Host and Port on which kafka is running.
* KEY_SERIALIZER_CLASS_CONFIG - Serializer class to be used for the key.
* VALUE_SERIALIZER_CLASS_CONFIG - Serializer class to be used for the value.
*       we are using StringSerializer for both keys and values.
* */

@Configuration
@EnableKafka
public class KafkaProducerConfig {

    @Value("${kafka.address}")
    private String bootstrapServers;

    @Bean
    public Map<String, Object> producerConfigString(){
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers);
        props.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

    @Bean
    public Map<String, Object> producerConfigByte() {
        // ProducerFactory with Bytes serializer
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class);
        return props;
    }
/*
We can use RoutingKafkaTemplate when we have multiple producers with different configurations
and we want to select producer at runtime based on the topic name.
    @Bean
    public RoutingKafkaTemplate routingTemplate(GenericApplicationContext context) {
        DefaultKafkaProducerFactory<Object, Object> bytesPF = new DefaultKafkaProducerFactory<>(producerConfigByte());
        context.registerBean(DefaultKafkaProducerFactory.class, "bytesPF", bytesPF);
        DefaultKafkaProducerFactory<Object, Object> stringPF = new DefaultKafkaProducerFactory<>(producerConfigString());
        context.registerBean(DefaultKafkaProducerFactory.class, "stringPF", stringPF);

        Map<Pattern, ProducerFactory<Object, Object>> map = new LinkedHashMap<>();
        map.put(Pattern.compile(".*-bytes"), bytesPF);
        map.put(Pattern.compile("top.*"), stringPF);
        return new RoutingKafkaTemplate(map);
    }*/

    @Bean
    public ProducerFactory<String, User> userProducerFactory(){
        return new DefaultKafkaProducerFactory<>(producerConfigString());
    }

    @Bean
    public ProducerFactory<String, String> producerFactory(){
        return new DefaultKafkaProducerFactory<>(producerConfigString());
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public KafkaTemplate<String, User> kafkaTemplateUser(){
        return new KafkaTemplate<>(userProducerFactory());
    }

    /*If we don’t want to work with Futures, we can register a ProducerListener instead:
    @Bean
    KafkaTemplate<String, String> kafkaTemplate() {
        KafkaTemplate<String, String> kafkaTemplate =
                new KafkaTemplate<>(producerFactory());
        kafkaTemplate.setProducerListener(new ProducerListener<String, String>() {
            @Override
            public void onSuccess(
                    ProducerRecord<String, String> producerRecord,
                    RecordMetadata recordMetadata) {

                log.info("ACK from ProducerListener message: {} offset:  {}",
                        producerRecord.value(),
                        recordMetadata.offset());
            }
        });
        return kafkaTemplate;
    }*/
}
