package com.cetin.education.spring.event.event.listener;

import com.cetin.education.spring.event.event.ReservationCreateEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ReservationEventListener {

    @EventListener
    public void reservationEventHandler(ReservationCreateEvent reservationCreateEvent) throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("Event Listener ==> "+reservationCreateEvent.getSource().toString());
    }
}
