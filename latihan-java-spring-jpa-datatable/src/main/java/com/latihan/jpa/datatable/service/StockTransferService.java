package com.latihan.jpa.datatable.service;

import com.latihan.jpa.datatable.dto.StockTransferDto;
import com.latihan.jpa.datatable.entity.StockInboundEntity;
import com.latihan.jpa.datatable.util.Page;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;

import java.util.List;

public interface StockTransferService {

    Page<StockTransferDto> findAll(DataTablesInput input, String stockType);

}
