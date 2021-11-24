package com.cetin.education.spring.rabbitmq.producer;

import com.cetin.education.spring.rabbitmq.model.Notification;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.UUID;

@Service
public class NotificationProducer {

    @Value("${sr.rabbit.routing.name}")
    private String routingKey;

    @Value("${sr.rabbit.exchange.name}")
    private String exchangeName;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void sendToQueue(Notification notification){
        System.out.println("Notification Sent Id:"+notification.getNotificationId());
        rabbitTemplate.convertAndSend(exchangeName, routingKey,notification);
    }
}
