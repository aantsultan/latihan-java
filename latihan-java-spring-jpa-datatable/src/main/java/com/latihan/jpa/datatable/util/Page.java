package com.latihan.jpa.datatable.util;

import lombok.Data;

import java.util.List;

@Data
public class Page<T> {

    private List<T> list;
    private int draw;
    private long totalRecords;
    private long totalFiltered;

    public Page(List<T> list){
        this.list = list;
    }

}
