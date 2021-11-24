package com.cetin.education.spring.elasticsearch.controller;

import com.cetin.education.spring.elasticsearch.entity.Person;
import com.cetin.education.spring.elasticsearch.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {
    private final PersonRepository personRepository;

    @PostConstruct
    void init(){
        Person person = new Person();
        person.setId("123456");
        person.setName("Mehmet");
        person.setSurname("Cetin");
        person.setAddress("Alanya");
        person.setBirthday(Calendar.getInstance().getTime());
        personRepository.save(person);
    }


    @GetMapping("/{search}")
    private ResponseEntity<List<Person>> getPerson(@PathVariable String search){
        List<Person> persons = personRepository.getByCustomQuery(search);
        return ResponseEntity.ok(persons);
    }
}
