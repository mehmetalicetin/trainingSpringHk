package com.cetin.education.spring.kafka;

import com.cetin.education.spring.kafka.dto.KMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaListenerService {

    @KafkaListener(
            topics = "${ma.kafka.topic}",
            groupId = "${ma.kafka.group.id}"
    )
    public void listen(KMessage message){
        log.info("Message recieved.. MessageId : {} Message {} Date : {}",
                message.getId(),
                message.getMessage(),
                message.getMessageDate());
    }
}
