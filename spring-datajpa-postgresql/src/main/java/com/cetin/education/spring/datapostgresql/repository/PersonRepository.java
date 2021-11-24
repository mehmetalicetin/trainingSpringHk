package com.cetin.education.spring.datapostgresql.repository;

import com.cetin.education.spring.datapostgresql.entity.Person;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PersonRepository  extends PagingAndSortingRepository<Person, Long> {
}
