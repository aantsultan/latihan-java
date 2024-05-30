package com.latihan.java.spring.data.redis;

import com.latihan.java.spring.data.redis.model.Product;
import com.latihan.java.spring.data.redis.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RepositoryTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void repositoryTest(){
        Product product = Product.builder()
                .id("1")
                .name("Samsung A")
                .price(5_000_000L)
                .build();

        productRepository.save(product);

        Product product2 = productRepository.findById("1").get();
        assertEquals(product, product2);

        Map<Object, Object> map = redisTemplate.opsForHash().entries("products:1");
        assertEquals(product.getId(), map.get("id"));
        assertEquals(product.getName(), map.get("name"));
        assertEquals(product.getPrice().toString(), map.get("price"));
    }
}
