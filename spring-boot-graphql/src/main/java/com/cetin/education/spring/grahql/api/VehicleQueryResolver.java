package com.cetin.education.spring.grahql.api;

import com.cetin.education.spring.grahql.entity.Vehicle;
import com.cetin.education.spring.grahql.repo.VehicleRepository;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VehicleQueryResolver implements GraphQLQueryResolver {

    private final VehicleRepository vehicleRepository;

    public List<Vehicle> getVehicles(String type){
     return vehicleRepository.getByTypeLike(type);
    }

    public Optional<Vehicle> getById(Long id){
       return vehicleRepository.findById(id);
    }
}
