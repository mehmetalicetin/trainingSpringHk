package com.cetin.kafka;

import com.cetin.kafka.model.User;
import com.cetin.kafka.template.KafkaSenderExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafKaApplication implements CommandLineRunner {

    @Autowired
    KafkaSenderExample kafkaSenderExample;

    public static void main(String[] args) {
        SpringApplication.run(KafKaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User u1 = new User("Ali");
        User u2 = new User("Ali-4654");
        User u3 = new User("Ali-123");
        User u4 = new User("Ali-999");
        kafkaSenderExample.sendMessage(u1, "topic1");
        kafkaSenderExample.sendMessage(u2, "topic1");
        kafkaSenderExample.sendMessage(u3, "topic2");
        kafkaSenderExample.sendMessage(u4, "topic2");
    }
}
