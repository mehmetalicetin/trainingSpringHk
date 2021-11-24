package com.cetin.education.spring.restclient.api;

import com.cetin.education.spring.restclient.model.PersonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("/resttemplate")
public class RestClientController {

    public static final String uri = "http://localhost:8080/person";

    private final RestTemplate restTemplate;

    public RestClientController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public ResponseEntity<List<PersonDTO>> getPersonList() {
        ResponseEntity<List> responseEntity = restTemplate.getForEntity(uri+"/findAll", List.class);
        List<PersonDTO> personDTOList = responseEntity.getBody();
        return ResponseEntity.ok(personDTOList);
    }

    @GetMapping(value = "/findById/{id}", produces = {"application/json", "text/xml"})
    public ResponseEntity<PersonDTO> getPerson(@PathVariable("id") Long id) {
        ResponseEntity<PersonDTO> personDTO = restTemplate.getForEntity(uri+"/findById/"+id,PersonDTO.class);
        return ResponseEntity.ok(personDTO.getBody());
    }

    @PostMapping
    public ResponseEntity<PersonDTO> savePerson(@RequestBody PersonDTO person){
       ResponseEntity<PersonDTO> responseEntity = restTemplate.postForEntity(uri+"/add", person, PersonDTO.class);
       PersonDTO personDTO = responseEntity.getBody();
       return ResponseEntity.ok(personDTO);
    }
}
