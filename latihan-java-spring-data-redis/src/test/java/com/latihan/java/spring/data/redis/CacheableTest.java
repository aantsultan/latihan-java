package com.latihan.java.spring.data.redis;

import com.latihan.java.spring.data.redis.model.Product;
import com.latihan.java.spring.data.redis.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CacheableTest {

    @Autowired
    private ProductService productService;

    @Test
    void getTest(){
        Product product = productService.getProduct("001");
        assertEquals("001", product.getId());

        Product product2 = productService.getProduct("001"); // log seharusnya tidak muncul karena telah di-cache
        assertEquals(product, product2);

        Product product3 = productService.getProduct("001"); // log seharusnya tidak muncul karena telah di-cache
        assertEquals(product, product3);

        Product product4 = productService.getProduct("001"); // log seharusnya tidak muncul karena telah di-cache
        assertEquals(product, product4);
    }

    @Test
    void saveTest(){
        Product product = Product.builder()
                .id("P002")
                .name("p-sample")
                .price(10L)
                .build();
        productService.save(product);

        // seharusnya get tidak di-exec yang ditandakan oleh tidak adanya log get product
        Product product2 = productService.getProduct("P002");
        assertEquals(product, product2);
    }

    @Test
    void removeTest(){
        Product product = productService.getProduct("P003");
        assertNotNull(product);

        productService.remove("P003");

        // seharusnya function getProduct dipanggil kembali karena id P003 telah diremove dalam cache
        Product product2 = productService.getProduct("P003");
        assertEquals(product, product2);
    }

}
