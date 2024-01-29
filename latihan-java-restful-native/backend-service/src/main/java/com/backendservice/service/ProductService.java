package com.backendservice.service;

import com.common.util.dto.CommonResponseDto;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    ResponseEntity<CommonResponseDto> retrieveAllProduct(Integer page, Integer total);
}
