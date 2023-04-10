package com.backendservice.controller;

import com.backendservice.service.ProductService;
import com.common.util.dto.CommonResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/product/get/page/{page}/total/{total}")
    public ResponseEntity<CommonResponseDto> retrieveAllProduct(
            @PathVariable Integer page,
            @PathVariable Integer total
    ){
        return service.retrieveAllProduct(page, total);
    }

}
