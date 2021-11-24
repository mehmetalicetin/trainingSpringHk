package com.cetin.education.spring.websocket.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class WsMessage implements Serializable {
    private String sender;
    private String message;
}
