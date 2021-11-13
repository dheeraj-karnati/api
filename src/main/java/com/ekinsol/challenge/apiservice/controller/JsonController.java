package com.ekinsol.challenge.apiservice.controller;

import com.ekinsol.challenge.apiservice.jpa.PersonEntity;
import com.ekinsol.challenge.apiservice.repository.PersonRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonController {

    private final PersonRepository personRepository;

    public JsonController(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/api/v1/persons")
    public Iterable<PersonEntity> getData() {
        return personRepository.findAll();
    }

    @PostMapping("/api/v1/persons")
    public void addData(@RequestBody PersonEntity tableData) {
        personRepository.save(tableData);
    }
}
