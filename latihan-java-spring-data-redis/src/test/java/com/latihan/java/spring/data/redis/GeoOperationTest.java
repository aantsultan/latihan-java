package com.latihan.java.spring.data.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class GeoOperationTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    void geoTest() {
        GeoOperations<String, String> geo = redisTemplate.opsForGeo();
        // contoh data di sekitar monas, jakarta
        geo.add("sellers", new Point(106.822877, -6.178166), "Toko A");
        geo.add("sellers", new Point(106.821332, -6.177531), "Toko B");

        Distance distance = geo.distance("sellers", "Toko A", "Toko B", Metrics.KILOMETERS);
        assertEquals(0.1849, distance.getValue());

        GeoResults<RedisGeoCommands.GeoLocation<String>> sellers = geo.search("sellers",
                new Circle(
                        new Point(106.822142, -6.177659), // titik pusat lingkaran
                        new Distance(5, Metrics.KILOMETERS) // radius 5 km
                ));

        // total toko yang ada di radius 5 kilometer dari titik pusat lingkaran
        assertEquals(2, sellers.getContent().size());

        //expected data, toko B lebih dekat dengan titik pusat GeoResults sellers
        assertEquals("Toko B", sellers.getContent().get(0).getContent().getName());
        //expected data, toko A lebih jauh dengan titik pusat GeoResults sellers
        assertEquals("Toko A", sellers.getContent().get(1).getContent().getName());

        redisTemplate.delete("sellers");
    }

}
