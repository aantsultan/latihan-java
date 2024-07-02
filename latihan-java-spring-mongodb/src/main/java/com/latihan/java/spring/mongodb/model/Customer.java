package com.latihan.java.spring.mongodb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public @Data class Customer {

    @Id
    private String id;

    private String firstName;
    private String lastName;

}
