package com.latihan.java.spring.data.redis.service;

import com.latihan.java.spring.data.redis.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService{

    @Cacheable(value = "products", key = "#id")
    @Override
    public Product getProduct(String id) {
        log.info("Get product {}", id);
        return Product.builder()
                .id(id)
                .name("sample")
                .price(100L)
                .build();
    }

    @CachePut(value = "products", key = "#product.id")
    @Override
    public Product save(Product product) {
        log.info("Save product {}", product);
        return product;
    }

    @CacheEvict(value = "products", key = "#id")
    @Override
    public void remove(String id) {
        log.info("Remove product {}", id);
    }
}
