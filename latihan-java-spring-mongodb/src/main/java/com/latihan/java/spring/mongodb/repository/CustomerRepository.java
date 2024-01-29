package com.latihan.java.spring.mongodb.repository;

import com.latihan.java.spring.mongodb.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
