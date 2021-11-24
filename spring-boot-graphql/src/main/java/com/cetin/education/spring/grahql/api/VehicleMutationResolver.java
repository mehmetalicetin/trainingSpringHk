package com.cetin.education.spring.grahql.api;

import com.cetin.education.spring.grahql.dto.VehicleConverter;
import com.cetin.education.spring.grahql.dto.VehicleDTO;
import com.cetin.education.spring.grahql.entity.Vehicle;
import com.cetin.education.spring.grahql.repo.VehicleRepository;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class VehicleMutationResolver implements GraphQLMutationResolver {

    private final VehicleRepository vehicleRepository;

    private final VehicleConverter vehicleConverter;

    public Vehicle createVehicle(VehicleDTO vehicleDTO){
        if(vehicleDTO != null){
            Vehicle vehicle = vehicleConverter.dtoToEntity(vehicleDTO);
            return vehicleRepository.save(vehicle);
        }
        else
            throw new RuntimeException();
    }
}
