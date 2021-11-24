package com.cetin.education.spring.kafka.controller;

import com.cetin.education.spring.kafka.dto.KMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/kmessage")
@RequiredArgsConstructor
public class ResourceController {
    private final KafkaTemplate<String, KMessage> kafkaTemplate;


    @Value("${ma.kafka.topic}")
    private String topic;

    @PostMapping
    public void sendMessage(@RequestBody KMessage kMessage){
        kafkaTemplate.send(topic, UUID.randomUUID().toString(), kMessage);
    }
}
