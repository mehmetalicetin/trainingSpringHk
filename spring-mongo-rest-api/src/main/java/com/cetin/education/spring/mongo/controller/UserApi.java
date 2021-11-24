package com.cetin.education.spring.mongo.controller;

import com.cetin.education.spring.mongo.entity.User;
import com.cetin.education.spring.mongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserApi {

    @Autowired
    UserRepository userRepository;


    @PostMapping("/add")
    public ResponseEntity<User> add(@RequestBody User user) {
        return ResponseEntity.ok(userRepository.save(user));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userRepository.findAll());
    }
}
