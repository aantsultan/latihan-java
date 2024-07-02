package com.latihan.java.spring.apache.ignite.controller;

import com.latihan.java.spring.apache.ignite.dto.EmployeeDto;
import com.latihan.java.spring.apache.ignite.dto.ResponseDto;
import com.latihan.java.spring.apache.ignite.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }


    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> get(@PathVariable("id") Integer id){
        return service.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> save(@RequestBody EmployeeDto dto){
        return service.save(dto);
    }

}
