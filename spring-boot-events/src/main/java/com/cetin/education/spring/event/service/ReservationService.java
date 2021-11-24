package com.cetin.education.spring.event.service;

import com.cetin.education.spring.event.api.BookingApi;
import com.cetin.education.spring.event.event.ReservationCreateEvent;
import com.cetin.education.spring.event.model.HotelBookRequest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private final ApplicationEventPublisher applicationEventPublisher;

    public ReservationService(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Async
    public void publishReservationEvent(HotelBookRequest hotelBookRequest){
        applicationEventPublisher.publishEvent(new ReservationCreateEvent(hotelBookRequest));
    }
}
