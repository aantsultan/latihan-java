package com.latihan.java.spring.webflux.service;

import com.latihan.java.spring.webflux.model.InventoryClosingPeriod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class InventoryClosingPeriodServiceImpl implements InventoryClosingPeriodService {

    @Override
    public void saveAll(List<InventoryClosingPeriod> closingPeriods) {
//        for (InventoryClosingPeriod closingPeriod : closingPeriods) {
//            log.info("Save data : {}", closingPeriod.getSku());
//        }
        log.info("Save data is done");
    }
}
