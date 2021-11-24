package com.cetin.education.spring.grahql.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class VehicleDTO {
    @Getter
    @Setter
    private String type;

    @Getter
    @Setter
    private String modelCode;

    @Getter
    @Setter
    private String brandName;
}
