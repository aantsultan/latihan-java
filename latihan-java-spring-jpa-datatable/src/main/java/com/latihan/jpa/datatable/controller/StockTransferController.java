package com.latihan.jpa.datatable.controller;

import com.latihan.jpa.datatable.dto.StockTransferDto;
import com.latihan.jpa.datatable.service.StockTransferService;
import com.latihan.jpa.datatable.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockTransferController {

    @Autowired
    private StockTransferService stockTransferService;

    @GetMapping("/stock-transfers")
    public Page<StockTransferDto> list(
            @RequestBody DataTablesInput input,
            @RequestParam(value = "stockType") String stockType){
        return stockTransferService.findAll(input, stockType);
    }
}
