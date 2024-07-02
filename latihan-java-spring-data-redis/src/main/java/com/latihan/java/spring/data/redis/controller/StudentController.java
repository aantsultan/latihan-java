package com.latihan.java.spring.data.redis.controller;

import com.latihan.java.spring.data.redis.dto.ResponseDto;
import com.latihan.java.spring.data.redis.dto.StudentDto;
import com.latihan.java.spring.data.redis.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService service;

    @Autowired
    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ResponseDto> get() {
        return service.get();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> save(@RequestBody StudentDto request) {
        return service.save(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> findById(@PathVariable("id") String id){
        return service.findById(id);
    }

}
