package com.cetin.education.spring.mongo;

import com.cetin.education.spring.mongo.entity.User;
import com.cetin.education.spring.mongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@EnableMongoRepositories
@SpringBootApplication
public class MongoRestApiDemoApplication {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    void init(){
        User user = new User();
        user.setId("123456");
        user.setName("Mehmet Ali");
        user.setSurname("Cetin");
        Map<String, String> properties = new HashMap<>();
        properties.put("max-name-len","45");
        user.setProperties(properties);
        userRepository.save(user);
    }

    public static void main(String[] args) {
        SpringApplication.run(MongoRestApiDemoApplication.class, args);
    }

}
