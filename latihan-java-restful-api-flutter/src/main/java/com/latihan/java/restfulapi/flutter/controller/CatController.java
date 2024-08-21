package com.latihan.java.restfulapi.flutter.controller;

import com.latihan.java.restfulapi.flutter.service.CatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CatController {

    private final CatService catService;

    @GetMapping(path = "/api/cat")
    public String get(){
        return catService.get();
    }

}
