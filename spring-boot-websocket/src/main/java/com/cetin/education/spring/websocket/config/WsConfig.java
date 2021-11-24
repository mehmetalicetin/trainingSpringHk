package com.cetin.education.spring.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker//web socketin message broker'ini enable yaptık.
public class WsConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat") // buraya mesajı gonderecem REsponse Channel
                .setAllowedOriginPatterns("*")//kim gelirse gelsin buna baglanabilsin
                .withSockJS(); //bu adress üzerinden konuşacam client tarafında.
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");//buradan mesajı alacam Request Cahnnel
    }
}
