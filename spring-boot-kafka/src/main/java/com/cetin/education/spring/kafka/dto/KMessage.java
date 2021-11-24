package com.cetin.education.spring.kafka.dto;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class KMessage {
    private String id = UUID.randomUUID().toString();
    private String message;
    private Date messageDate;
}
