package com.latihan.java.spring.data.redis;

import com.latihan.java.spring.data.redis.model.Product;
import com.latihan.java.spring.data.redis.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class TimeToLiveTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void repositoryTest() throws InterruptedException {
        Product product = Product.builder()
                .id("1")
                .name("Samsung A")
                .price(5_000_000L)
                .ttl(3L)
                .build();

        productRepository.save(product);
        assertTrue(productRepository.findById("1").isPresent());

        Thread.sleep(Duration.ofSeconds(5).toMillis());
        assertFalse(productRepository.findById("1").isPresent());

    }

}
