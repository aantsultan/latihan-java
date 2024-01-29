package com.latihan.jpa.datatable.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "trx_stock_outbound")
public @Data class StockOutboundEntity {

    @Id
    @GeneratedValue
    @Column(name = "stock_outbound_id")
    private Long stockOutboundId;
    @Column(name = "sales_group_id")
    private long salesGroupId;
    @Column(name = "warehouse_id")
    private long warehouseId;
    @Column(name = "reason")
    private String reason;

}
