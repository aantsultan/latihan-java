package com.latihan.java.spring.data.redis.repository;

import com.latihan.java.spring.data.redis.model.Product;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends KeyValueRepository<Product, String> {
}
