package com.cetin.education.spring.datapostgresql.dto;

import com.cetin.education.spring.datapostgresql.entity.Address;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
public class PersonDTO {
    private Long id;
    private String name;
    private String surname;
    private List<String> addressList;
}
