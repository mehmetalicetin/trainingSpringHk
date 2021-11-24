package com.cetin.education.spring.rabbitmq;

import com.cetin.education.spring.rabbitmq.model.Notification;
import com.cetin.education.spring.rabbitmq.producer.NotificationProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

@SpringBootApplication
public class RabbitApplication implements CommandLineRunner {

    @Autowired
    private NotificationProducer notificationProducer;

    public static void main(String[] args) {
        SpringApplication.run(RabbitApplication.class,args);
    }

    @Override
    public void run(String... args) throws ExecutionException, InterruptedException {
        System.out.println("EXECUTING : command line runner");

        List<Future> futureList = new ArrayList<>();

        ExecutorService executorService = Executors.newScheduledThreadPool(10);
        for (int i=0; i<50; i++) {
         Future future = executorService.submit(() -> {
                init();
            });
         futureList.add(future);
        }

        for (Future future: futureList) {
            future.get();
        }
    }

    public void init(){
        Notification notification = new Notification();
        notification.setNotificationId(UUID.randomUUID().toString());
        notification.setCreatedAt(new Date());
        notification.setMessage("Hello, I'm a notification");
        notification.setSeen(Boolean.FALSE);

        notificationProducer.sendToQueue(notification);
    }
}
