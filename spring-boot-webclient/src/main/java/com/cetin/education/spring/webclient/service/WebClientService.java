package com.cetin.education.spring.webclient.service;

import com.cetin.education.spring.webclient.model.PersonDTO;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.print.attribute.standard.Media;

@Data
@Service
public class WebClientService {

    private final WebClient webClient;

    public Flux<PersonDTO> getPersonList() {
        return webClient.get()
                .uri("/findAll")
                .retrieve()
                .bodyToFlux(PersonDTO.class);
    }

    public Flux<PersonDTO> getPersonListByExchange() {
        return webClient.get()
                .uri("/findAll")
                .retrieve()
                .bodyToFlux(PersonDTO.class);
    }

    public Mono<PersonDTO>  findById(Integer id) {
        return webClient.get()
                .uri("/findById/{id}",id)
                .retrieve()
                .bodyToMono(PersonDTO.class);
    }

    public Mono<PersonDTO> savePerson(PersonDTO personDTO) {
        return webClient.post()
                .uri("/add")
                .body(Mono.just(personDTO), PersonDTO.class)
                .retrieve()
                .bodyToMono(PersonDTO.class);
    }
}
