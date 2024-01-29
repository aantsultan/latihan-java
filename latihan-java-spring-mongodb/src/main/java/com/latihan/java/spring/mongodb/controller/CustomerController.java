package com.latihan.java.spring.mongodb.controller;

import com.latihan.java.spring.mongodb.dto.CustomerDto;
import com.latihan.java.spring.mongodb.dto.ResponseDto;
import com.latihan.java.spring.mongodb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService service;

    @Autowired
    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ResponseDto> get(){
        return service.get();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public String save(@RequestBody CustomerDto customerDto){
        return service.save(customerDto);
    }

}
