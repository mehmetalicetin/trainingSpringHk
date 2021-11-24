package com.cetin.kafka.listener;

import com.cetin.kafka.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListenerExample {
    Logger LOG = LoggerFactory.getLogger(KafkaListenerExample.class);

    @KafkaListener(topics = "topic1", groupId = "group1", containerFactory = "kafkaListenerContainerFactory")
    void listener(String data) {
        LOG.info(data);
    }

    @KafkaListener(topics = "topic2", groupId = "group1", containerFactory = "userKafkaListenerContainerFactory")
    void listener2(User data) {
        LOG.info(data.toString());
    }

//    @KafkaListener(
//            topics = "topic1, topic2",
//            groupId = "group1")
//    void commonListenerForMultipleTopics(String message) {
//        LOG.info("MultipleTopicListener - {}", message);
//    }
}
