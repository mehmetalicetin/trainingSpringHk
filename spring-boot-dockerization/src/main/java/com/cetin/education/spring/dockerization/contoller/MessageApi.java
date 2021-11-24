package com.cetin.education.spring.dockerization.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageApi {

    @GetMapping
    public String message(){
        return "Hello inside docker image";
    }
}
