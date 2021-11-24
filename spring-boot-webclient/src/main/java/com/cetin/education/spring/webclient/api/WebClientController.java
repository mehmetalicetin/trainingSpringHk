package com.cetin.education.spring.webclient.api;

import com.cetin.education.spring.webclient.model.PersonDTO;
import com.cetin.education.spring.webclient.service.WebClientService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/webclient")
@Data
public class WebClientController {

    private final WebClientService webClientService;

    @GetMapping(value = "/findAll", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Flux<PersonDTO> getPersonList() {
        return webClientService.getPersonList();
    }

    @GetMapping(value = "/findById/{id}", produces = {"application/json", "text/xml"})
    @ResponseStatus(HttpStatus.OK)
    public Mono<PersonDTO> findById(@PathVariable("id") Integer id) {
        return webClientService.findById(id);
    }

    @PostMapping(value = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<PersonDTO> savePerson(@RequestBody PersonDTO personDTO) {
        return webClientService.savePerson(personDTO);
    }
}
