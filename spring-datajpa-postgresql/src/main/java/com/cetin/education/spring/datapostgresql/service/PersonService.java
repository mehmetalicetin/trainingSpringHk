package com.cetin.education.spring.datapostgresql.service;

import com.cetin.education.spring.datapostgresql.dto.PersonDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PersonService {

    PersonDTO save(PersonDTO personDTO);

    void delete(Long id);

    PersonDTO getById(Long id);

    List<PersonDTO> getAll();

    Page<PersonDTO> getAll(Pageable pageable);
}
