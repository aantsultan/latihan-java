package com.latihan.java.spring.webflux.service;

import com.latihan.java.spring.webflux.model.InventorySummary;

import java.util.List;

public interface InventorySummaryService {

    List<InventorySummary> findAll();

}
