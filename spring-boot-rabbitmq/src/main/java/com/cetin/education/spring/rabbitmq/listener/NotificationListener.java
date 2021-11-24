package com.cetin.education.spring.rabbitmq.listener;

import com.cetin.education.spring.rabbitmq.model.Notification;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationListener {

    @RabbitListener(queues = "haydi-kodlayalim-queue")
    public void handleMessage(Notification notification){
        System.out.println("Mesaj alindi");
        System.out.println(notification.toString());
    }
}
