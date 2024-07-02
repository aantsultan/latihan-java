package com.latihan.java.spring.webflux.service;

import com.latihan.java.spring.webflux.model.InventoryClosingPeriod;

import java.util.List;

public interface InventoryClosingPeriodService {

    void saveAll(List<InventoryClosingPeriod> closingPeriods);
    void save(InventoryClosingPeriod inventoryClosingPeriod);

}
