package com.cetin.kafka.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }
}
