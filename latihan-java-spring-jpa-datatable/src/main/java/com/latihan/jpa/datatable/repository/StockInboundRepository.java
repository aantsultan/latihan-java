package com.latihan.jpa.datatable.repository;

import com.latihan.jpa.datatable.entity.StockInboundEntity;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockInboundRepository extends DataTablesRepository<StockInboundEntity, Long> {
}
