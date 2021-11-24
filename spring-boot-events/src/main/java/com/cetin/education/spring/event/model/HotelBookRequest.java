package com.cetin.education.spring.event.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class HotelBookRequest {
    private String userId;
    private String hotelId;
}
