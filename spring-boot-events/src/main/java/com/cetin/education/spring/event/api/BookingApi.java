package com.cetin.education.spring.event.api;

import com.cetin.education.spring.event.model.HotelBookRequest;
import com.cetin.education.spring.event.service.ReservationService;
import lombok.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookingApi {

    private final ReservationService reservationService;

    @PostMapping
    public void bookHotel(@RequestBody HotelBookRequest hotelBookRequest){
        reservationService.publishReservationEvent(hotelBookRequest);
        System.out.println("User request process has been initialized..."+hotelBookRequest);
    }
}
