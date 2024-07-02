package com.latihan.java.spring.webflux.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventorySummary {

    private Long id;
    private String sku;
    private Long goodStockHand;

}
