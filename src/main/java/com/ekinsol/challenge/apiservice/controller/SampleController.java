package com.ekinsol.challenge.apiservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping
    public String greeting() {
        return "Test integration";
    }

    @GetMapping(path = "/api/hello")
    public String getResp(){
        return  "Protected request";
    }
}
