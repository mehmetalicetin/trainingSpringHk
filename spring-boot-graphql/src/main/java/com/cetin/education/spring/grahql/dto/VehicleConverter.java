package com.cetin.education.spring.grahql.dto;

import com.cetin.education.spring.grahql.entity.Vehicle;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class VehicleConverter implements IConverterDto<VehicleDTO, Vehicle> {
    @Override
    public Vehicle dtoToEntity(VehicleDTO vehicleDTO) {
        Vehicle vehicle = new Vehicle();
        vehicle.setType(vehicleDTO.getType());
        vehicle.setBrandName(vehicleDTO.getBrandName());
        vehicle.setModelCode(vehicleDTO.getModelCode());
        vehicle.setLaunchDate(new Date());
        return vehicle;
    }
}
