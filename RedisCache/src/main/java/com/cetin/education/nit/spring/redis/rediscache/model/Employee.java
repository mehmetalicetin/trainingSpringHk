package com.cetin.education.nit.spring.redis.rediscache.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee implements Serializable {

    @Id
    @GeneratedValue
    private Integer empId;
    private String empName;
    private Double empSale;
}
