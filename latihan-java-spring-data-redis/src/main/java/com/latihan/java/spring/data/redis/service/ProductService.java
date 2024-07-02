package com.latihan.java.spring.data.redis.service;

import com.latihan.java.spring.data.redis.model.Product;

public interface ProductService {

    Product getProduct(String id);

    Product save(Product product);

    void remove(String id);

}
