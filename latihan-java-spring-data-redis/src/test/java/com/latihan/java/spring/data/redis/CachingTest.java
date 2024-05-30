package com.latihan.java.spring.data.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class CachingTest {

    @Autowired
    private CacheManager cacheManager;

    @Test
    void cacheTest(){
        Cache cache = cacheManager.getCache("scores");
        cache.put("aant", 100);
        cache.put("sultan", 50);

        assertEquals(100, cache.get("aant", Integer.class));
        assertEquals(50, cache.get("sultan", Integer.class));

        cache.evict("aant");
        cache.evict("sultan");

        assertNull(cache.get("aant"));
        assertNull(cache.get("sultan"));
    }

}
