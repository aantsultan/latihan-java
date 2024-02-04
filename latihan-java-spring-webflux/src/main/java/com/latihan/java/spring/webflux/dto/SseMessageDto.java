package com.latihan.java.spring.webflux.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SseMessageDto {

    private Long currentData;
    private Long totalData;
    private String type;

}
