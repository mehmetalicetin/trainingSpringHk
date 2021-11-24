package com.cetin.education.spring.kafka.controller;

import com.cetin.education.spring.kafka.model.SimpleModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kafka")
@RequiredArgsConstructor
public class SimpleController {

    private final KafkaTemplate<String, SimpleModel> kafkaTemplate;

    @PostMapping
    public void post(@RequestBody SimpleModel simpleModel){
        kafkaTemplate.send("myTopic",simpleModel);
    }

    @KafkaListener(topics = "myTopic")
    public void getFromKafka(SimpleModel simpleModel){
        System.out.println("Message recieved.. Field1 : "+simpleModel.getField1()+" Field2 "+ simpleModel.getField2());
    }

}
