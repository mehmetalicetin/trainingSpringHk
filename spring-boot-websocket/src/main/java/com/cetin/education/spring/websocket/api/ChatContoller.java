package com.cetin.education.spring.websocket.api;

import com.cetin.education.spring.websocket.model.WsMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatContoller {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat")
    //@SendTo("/topic") //gelen tum mesajları butun kullanicilar gorebiliyor
    //@SendToUser // belirli bir kullaniciya gondermek icin
    public void chatEndPoint(@Payload WsMessage wsMessage){
        System.out.println(wsMessage);
        messagingTemplate.convertAndSend("/topic",wsMessage);
    }

    //@Payload gelen mesajın body'si


}
