package com.latihan.java.spring.webflux.service;

import com.latihan.java.spring.webflux.model.InventorySummary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventorySummaryServiceImpl implements InventorySummaryService {

    @Override
    public List<InventorySummary> findAll() {
        List<InventorySummary> result = new ArrayList<>();
        for (int i = 0; i < 100_000; i++) {
            InventorySummary inventorySummary = InventorySummary.builder()
                    .id(Long.parseLong(String.valueOf(i)))
                    .sku("SKU " + i)
                    .goodStockHand(10L)
                    .build();

            result.add(inventorySummary);
        }
        return result;
    }
}
