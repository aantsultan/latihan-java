package com.latihan.java.spring.apache.ignite.model;

import lombok.Data;
import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.io.Serializable;


public @Data class Employee implements Serializable {

    @QuerySqlField(index = true)
    private Integer id;

    @QuerySqlField(index = true)
    private String name;

    @QuerySqlField(index = true)
    private boolean isEmployed;

}
