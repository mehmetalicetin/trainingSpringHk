package com.cetin.spring.kafka.basic;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.config.TopicConfig;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication(scanBasePackages = "com.cetin.spring.kafka.basic")
public class KafkaBasicApp {
    public static void main(String[] args) {
        SpringApplication.run(KafkaBasicApp.class, args);
    }

    /*If you define a KafkaAdmin bean in your application context,
        it can automatically add topics to the broker*/
    @Bean
    public KafkaAdmin admin(){
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic topic1(){
        return TopicBuilder.name("thing1")
                .partitions(10)
                .replicas(1)
                .compact()
                .build();
    }

    @Bean
    public NewTopic topic2(){
        return TopicBuilder.name("thing2")
                .partitions(10)
                .replicas(3)
                .config(TopicConfig.COMPRESSION_TYPE_CONFIG,"zstd")
                .build();
    }

    @Bean
    public NewTopic topic3(){
        return TopicBuilder.name("thing3")
                .assignReplicas(0, Arrays.asList(0,1))
                .assignReplicas(1, Arrays.asList(1,2))
                .assignReplicas(2, Arrays.asList(2,0))
                .config(TopicConfig.COMPRESSION_TYPE_CONFIG,"zstd")
                .build();
    }

//    @Bean
//    public NewTopic topic4() {
//        return TopicBuilder.name("defaultBoth")
//                .build();
//    }
//    @Bean
//    public NewTopic topic5() {
//        return TopicBuilder.name("defaultPart")
//                .replicas(1)
//                .build();
//    }
//    @Bean
//    public NewTopic topic6() {
//        return TopicBuilder.name("defaultRepl")
//                .partitions(3)
//                .build();
//    }

    @Bean
    public KafkaAdmin.NewTopics topics456(){
        return new KafkaAdmin.NewTopics(
                TopicBuilder.name("defaultBoth")
                .build(),
                TopicBuilder.name("defaultPart")
                .replicas(1)
                .build(),
                TopicBuilder.name("defaultRepl")
                .partitions(3)
                .build()
        );
    }


    @Bean
    public NewTopic topic(){
        return TopicBuilder.name("topic1")
                .partitions(10)
                .replicas(1)
                .build();
    }

    @KafkaListener(id = "myId", topics = "topic1")
    public void listen(String in){
        System.out.println(in);
    }

//    @Bean
//    public ApplicationRunner runner(KafkaTemplate<String, String> kafkaTemplate){
//        return args ->{
//          kafkaTemplate.send("topic1","test");
//        };
//    }

    @Bean
    public ApplicationRunner runner(KafkaTemplate<Integer, String> kafkaTemplate){
        return args ->{
           sendToKafka("testData",kafkaTemplate);
        };
    }

    public void sendToKafka(String data, KafkaTemplate<Integer, String> kafkaTemplate) {
        final ProducerRecord<Integer, String> record = createRecord(data);
        ListenableFuture<SendResult<Integer, String>> future = kafkaTemplate.send(record);
        future.addCallback(new KafkaSendCallback<Integer, String>() {
            @Override
            public void onSuccess(SendResult<Integer, String> result) {
                handleSuccess(data);
            }
            @Override
            public void onFailure(KafkaProducerException ex) {
                handleFailure(data, record, ex);
            }
        });
    }

    private void handleFailure(String data, ProducerRecord<Integer, String> record, KafkaProducerException ex) {

    }

    private void handleSuccess(String data) {
        System.out.println("Success "+data);
    }

    public ProducerRecord<Integer, String> createRecord(String data) {
        ProducerRecord<Integer, String> producerRecord = new ProducerRecord<Integer,String>("topic1",1,data);
        return producerRecord;
    }

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }

    //the DefaultKafkaProducerFactory creates a singleton producer used by all clients,
    @Bean
    public ProducerFactory<Integer, String> producerFactory(){
        return new DefaultKafkaProducerFactory(producerConfigs());
    }

    @Bean
    public KafkaTemplate<Integer, String> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public KafkaTemplate<String, String> stringKafkaTemplate(ProducerFactory<String, String> pf){
        return new KafkaTemplate<>(pf);
    }

    @Bean
    public KafkaTemplate<String, byte[]> bytesKafkaTemplate(ProducerFactory<String, byte[]> pf){
        return new KafkaTemplate<String, byte[]>(pf,
                Collections.singletonMap(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class));
    }
}
