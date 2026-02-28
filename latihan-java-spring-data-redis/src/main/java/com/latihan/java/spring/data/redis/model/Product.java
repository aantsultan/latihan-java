package com.latihan.java.spring.data.redis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.keyvalue.annotation.KeySpace;
import org.springframework.data.redis.core.TimeToLive;

import java.io.Serial;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@KeySpace("products")
public class Product implements Serializable {

    @Serial
    private static final long serialVersionUID = -8406220782228402757L;

    @Id
    private String id;
    private String name;
    private Long price;

    @TimeToLive(unit = TimeUnit.SECONDS)
    private Long ttl = -1L; // -1 will not expire
}
