package com.cetin.education.spring.webclient.model;

import lombok.Data;

import java.util.List;

@Data
public class PersonDTO {
    private Long id;
    private String name;
    private String surname;
    private List<String> addressList;
}
