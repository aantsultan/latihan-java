package com.latihan.jpa.datatable.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trx_stock_inbound")
public class StockInboundEntity {

    @Id
    @GeneratedValue
    @Column(name = "stock_inbound_id")
    private Long stockInboundId;
    @Column(name = "sales_group_id")
    private long salesGroupId;
    @Column(name = "warehouse_id")
    private long warehouseId;

}
