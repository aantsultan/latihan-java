package com.latihan.java.spring.mongodb.service;

import com.latihan.java.spring.mongodb.dto.CustomerDto;
import com.latihan.java.spring.mongodb.dto.ResponseDto;
import com.latihan.java.spring.mongodb.model.Customer;
import com.latihan.java.spring.mongodb.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<ResponseDto> get() {
        List<Customer> result = repository.findAll();
        List<CustomerDto> convert = result.stream().map(data ->
                CustomerDto.builder()
                        .id(data.getId())
                        .firstName(data.getFirstName())
                        .lastName(data.getLastName()).build()).collect(Collectors.toList());
        return new ResponseEntity<>(ResponseDto.builder()
                .data(convert)
                .build(), HttpStatus.OK);
    }

    @Override
    public String save(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        try {
            repository.save(customer);
            return HttpStatus.OK.name();
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR.name();
        }
    }
}
