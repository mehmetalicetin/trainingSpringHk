package com.cetin.spring.kafka.repliyingkafkatemplate;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.errors.TimeoutException;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.KafkaNull;
import org.springframework.kafka.support.SendResult;
import org.springframework.kafka.support.SimpleKafkaHeaderMapper;
import org.springframework.kafka.support.converter.MessagingMessageConverter;
import org.springframework.messaging.handler.annotation.SendTo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@SpringBootApplication(scanBasePackages ="com.cetin.spring.kafka.repliyingkafkatemplate")
public class KRequestingApplication {
    public static void main(String[] args) {
        SpringApplication.run(KRequestingApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner(ReplyingKafkaTemplate<String,String,String> template){
        return args ->{
            ProducerRecord<String, String> record = new ProducerRecord<>("kRequests","foo", "foo");
            RequestReplyFuture<String, String, String> replyFuture = template.sendAndReceive(record);
            SendResult<String, String> sendResult = replyFuture.getSendFuture().get(10, TimeUnit.SECONDS);
            System.out.println("Sent Ok: "+sendResult.getRecordMetadata());
//            ConsumerRecord<String, String> consumerRecord = replyFuture.get(10, TimeUnit.SECONDS);
//            System.out.println("Return Value: "+ consumerRecord.value());

//            RequestReplyFuture<String, String, String> future = template.sendAndReceive(record);
//            try {
//                future.get(10, TimeUnit.SECONDS); // send ok
//                //ConsumerRecord<String, String> consumerRecord = future.get(10, TimeUnit.SECONDS);
//
//            }
//            catch (InterruptedException e) {
//                System.out.println("InterruptedException :" + e.getMessage());
//            }
//            catch (ExecutionException e) {
//                System.out.println("ExecutionException :" + e.getMessage());
//            }
//            catch (TimeoutException e) {
//                System.out.println("TimeoutException : "+ e.getMessage());
//            }
       };
    }

    @Bean
    public ReplyingKafkaTemplate<String, String, String>
                    replyingKafkaTemplate(ProducerFactory<String,String> pf,
                                          ConcurrentMessageListenerContainer<String,String> repliesContainer){
        return new ReplyingKafkaTemplate<>(pf, repliesContainer);
    }

    @Bean
    public ConcurrentMessageListenerContainer<String, String> repliesContainer(ConcurrentKafkaListenerContainerFactory<String, String> containerFactory) {
        ConcurrentMessageListenerContainer<String, String> repliesContainer = containerFactory.createContainer("kReplies");
        repliesContainer.getContainerProperties().setGroupId("repliesGroup");
        repliesContainer.setAutoStartup(false);
        return repliesContainer;
    }

    @Bean
    public NewTopic kRequests(){
        return TopicBuilder.name("kRequests")
                .partitions(10)
                .replicas(2)
                .build();
    }

    @Bean
    public NewTopic kReplies(){
        return TopicBuilder.name("kReplies")
                .partitions(10)
                .replicas(2)
                .build();
    }

//    @KafkaListener(id = "server", topics = "kRequests")
//    @SendTo // use default replyTo expression
//    public Object listen(String in){
//        System.out.println("Server received: "+ in);
//        return in.equals("foo") ? in.toUpperCase() : KafkaNull.INSTANCE;
//    }
//
//    @Bean // not required if Jackson is on the classpath
//    public MessagingMessageConverter simpleMapperConverter() {
//        MessagingMessageConverter messagingMessageConverter = new MessagingMessageConverter();
//        messagingMessageConverter.setHeaderMapper(new SimpleKafkaHeaderMapper());
//        return messagingMessageConverter;
//    }
}
