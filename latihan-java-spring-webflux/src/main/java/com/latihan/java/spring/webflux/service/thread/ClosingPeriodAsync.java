package com.latihan.java.spring.webflux.service.thread;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.latihan.java.spring.webflux.constant.SSEConstant;
import com.latihan.java.spring.webflux.dto.SseMessageDto;
import com.latihan.java.spring.webflux.model.InventoryClosingPeriod;
import com.latihan.java.spring.webflux.model.InventorySummary;
import com.latihan.java.spring.webflux.service.InventoryClosingPeriodService;
import com.latihan.java.spring.webflux.service.InventorySummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClosingPeriodAsync {

    private final InventorySummaryService inventorySummaryService;
    private final InventoryClosingPeriodService inventoryClosingPeriodService;
    private final ObjectMapper mapper;

    @Autowired
    public ClosingPeriodAsync(InventorySummaryService inventorySummaryService, InventoryClosingPeriodService inventoryClosingPeriodService, ObjectMapper mapper) {
        this.inventorySummaryService = inventorySummaryService;
        this.inventoryClosingPeriodService = inventoryClosingPeriodService;
        this.mapper = mapper;
    }

    @Async("executorClosingPeriod")
    public void closingAsync(SseEmitter emitter) {
        List<InventorySummary> inventorySummaries = inventorySummaryService.findAll();
        List<InventoryClosingPeriod> inventoryClosingPeriods = new ArrayList<>();
        long currentData = 1L;
        long totalData = inventorySummaries.size();
        try {
            for (InventorySummary inventorySummary : inventorySummaries) {
                InventoryClosingPeriod inventoryClosingPeriod = InventoryClosingPeriod
                        .builder()
                        .id(inventorySummary.getId())
                        .sku(inventorySummary.getSku())
                        .goodStockHand(inventorySummary.getGoodStockHand())
                        .build();
                inventoryClosingPeriods.add(inventoryClosingPeriod);

                SseMessageDto messageDto = SseMessageDto.builder()
                        .currentData(currentData)
                        .totalData(totalData)
                        .type(SSEConstant.GET_TYPE)
                        .build();

                SseEmitter.SseEventBuilder event = SseEmitter.event()
                        .data(mapper.writeValueAsString(messageDto))
                        .id(String.valueOf(currentData))
                        .name(SSEConstant.SSE_NAME_CLOSING_PERIOD);
                emitter.send(event);

                currentData++;
            }

            // reset value
            currentData = 1L;
            totalData = inventoryClosingPeriods.size();

            for (InventoryClosingPeriod inventoryClosingPeriod : inventoryClosingPeriods) {
                inventoryClosingPeriodService.save(inventoryClosingPeriod);

                SseMessageDto messageDto = SseMessageDto.builder()
                        .currentData(currentData)
                        .totalData(totalData)
                        .type(SSEConstant.SAVE_TYPE)
                        .build();

                SseEmitter.SseEventBuilder event = SseEmitter.event()
                        .data(mapper.writeValueAsString(messageDto))
                        .id(String.valueOf(currentData))
                        .name(SSEConstant.SSE_NAME_CLOSING_PERIOD);
                emitter.send(event);
                currentData++;
            }

            emitter.send(SseEmitter.event().name(SSEConstant.SSE_NAME_CLOSING_PERIOD).data(SSEConstant.CLOSE));
        } catch (Exception e) {
            emitter.completeWithError(e);
        }

    }

}
