package com.latihan.java.spring.webflux.service.thread;

import com.latihan.java.spring.webflux.constant.SSEConstant;
import com.latihan.java.spring.webflux.dto.SseMessageDto;
import com.latihan.java.spring.webflux.model.InventoryClosingPeriod;
import com.latihan.java.spring.webflux.model.InventorySummary;
import com.latihan.java.spring.webflux.service.InventoryClosingPeriodService;
import com.latihan.java.spring.webflux.service.InventorySummaryService;
import com.latihan.java.spring.webflux.service.event.PublishEventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class ClosingPeriodAsyncStream {

    private final InventorySummaryService inventorySummaryService;
    private final InventoryClosingPeriodService inventoryClosingPeriodService;
    private final PublishEventService publishEventService;

    @Autowired
    public ClosingPeriodAsyncStream(InventorySummaryService inventorySummaryService, InventoryClosingPeriodService inventoryClosingPeriodService
            , PublishEventService publishEventService) {
        this.inventorySummaryService = inventorySummaryService;
        this.inventoryClosingPeriodService = inventoryClosingPeriodService;
        this.publishEventService = publishEventService;
    }

    @Async
    public void closingAsync() throws InterruptedException {
        log.info("Closing Async start");
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

                publishEventService.publish(messageDto);

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

                publishEventService.publish(messageDto);
                currentData++;
            }

            SseMessageDto messageDto = SseMessageDto.builder()
                    .currentData(currentData)
                    .totalData(totalData)
                    .type(SSEConstant.CLOSE)
                    .build();
            publishEventService.publish(messageDto);
            log.info("Closing Async END");

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new InterruptedException(e.getMessage());
        }

    }
}
