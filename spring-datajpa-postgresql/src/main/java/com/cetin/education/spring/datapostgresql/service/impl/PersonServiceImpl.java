package com.cetin.education.spring.datapostgresql.service.impl;

import com.cetin.education.spring.datapostgresql.dto.PersonDTO;
import com.cetin.education.spring.datapostgresql.entity.Address;
import com.cetin.education.spring.datapostgresql.entity.AddressTp;
import com.cetin.education.spring.datapostgresql.entity.Person;
import com.cetin.education.spring.datapostgresql.repository.AddressRepository;
import com.cetin.education.spring.datapostgresql.repository.PersonRepository;
import com.cetin.education.spring.datapostgresql.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;


    @Override
    @Transactional
    public PersonDTO save(PersonDTO personDTO) {
        Assert.notNull(personDTO.getName()," Name cannot be null");

        Person person = new Person();
        person.setId(personDTO.getId());
        person.setName(personDTO.getName());
        person.setSurname(personDTO.getSurname());

        Person dbPerson = personRepository.save(person);

        List<Address> addressList = new ArrayList<>();

        personDTO.getAddressList().forEach(x ->{
            Address address = new Address();
            address.setAddress(x);
            address.setAddressTp(AddressTp.HOME);
            address.setActive(true);
            address.setPerson(dbPerson);
            addressList.add(address);
        });

        addressRepository.saveAll(addressList);

        personDTO.setId(dbPerson.getId());

        return null;
    }

    @Override
    public void delete(Long id) {

    }


    @Override
    public PersonDTO getById(Long id) {
        PersonDTO personDTO = new PersonDTO();
        Optional<Person> personIterable = personRepository.findById(id);
        personIterable.ifPresent(person->{
            personDTO.setName(person.getName());
            personDTO.setSurname(person.getSurname());
            personDTO.setId(person.getId());
            List<String> addressList = person.getAddressList().stream().map(Address::getAddress).collect(Collectors.toList());
            personDTO.setAddressList(addressList);
        });
        return personDTO;
    }

    @Override
    public List<PersonDTO> getAll() {
        List<PersonDTO> personDTOS = new ArrayList<>();
        Iterable<Person> personIterable = personRepository.findAll();
        personIterable.forEach(x->{
            PersonDTO personDTO = new PersonDTO();
            personDTO.setName(x.getName());
            personDTO.setSurname(x.getSurname());
            personDTO.setId(x.getId());
            List<String> addressList = x.getAddressList().stream().map(Address::getAddress).collect(Collectors.toList());
            personDTO.setAddressList(addressList);
            personDTOS.add(personDTO);
        });
        return personDTOS;
    }

    @Override
    public Page<PersonDTO> getAll(Pageable pageable) {
        return null;
    }
}
