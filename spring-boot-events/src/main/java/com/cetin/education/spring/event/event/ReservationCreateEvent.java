package com.cetin.education.spring.event.event;

import org.springframework.context.ApplicationEvent;

public class ReservationCreateEvent extends ApplicationEvent {
    public ReservationCreateEvent(Object source) {
        super(source);
    }
}
