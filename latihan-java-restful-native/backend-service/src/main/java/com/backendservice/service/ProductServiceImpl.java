package com.backendservice.service;

import com.backendservice.dto.ProductResponseDto;
import com.backendservice.entity.ProductEntity;
import com.backendservice.mapper.ProductResponseMapper;
import com.backendservice.repository.ProductRepository;
import com.common.util.dto.CommonResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductResponseMapper productResponseMapper;

    @Override
    public ResponseEntity<CommonResponseDto> retrieveAllProduct(Integer page, Integer total) {
        Page<ProductEntity> productEntities = repository.getAllProduct(PageRequest.of(page, total));
        List<ProductResponseDto> productResponseDtoList =
                productEntities.stream()
                        .map(productResponseMapper)
                        .collect(Collectors.toList());

        long productSize = repository.count();
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        response.put("product", productResponseDtoList);
        response.put("page", page);
        response.put("total", total);
        response.put("size", productSize);
        CommonResponseDto responseDto = new CommonResponseDto(
                response,
                HttpStatus.OK.value(),
                HttpStatus.OK.name()
        );
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
