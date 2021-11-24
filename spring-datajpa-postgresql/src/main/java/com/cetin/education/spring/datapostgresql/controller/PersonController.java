package com.cetin.education.spring.datapostgresql.controller;

import com.cetin.education.spring.datapostgresql.dto.PersonDTO;
import com.cetin.education.spring.datapostgresql.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping("/add")
    public ResponseEntity<PersonDTO> add(@RequestBody PersonDTO person) {
        return ResponseEntity.ok(personService.save(person));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<PersonDTO>> findAll() {
        return ResponseEntity.ok(personService.getAll());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<PersonDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(personService.getById(id));
    }

}
