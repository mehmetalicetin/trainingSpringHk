package com.cetin.education.spring.datapostgresql.repository;

import com.cetin.education.spring.datapostgresql.entity.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AddressRepository extends PagingAndSortingRepository<Address,Long> {
}
