package com.latihan.java.spring.bc365.service;

import com.latihan.java.spring.bc365.dto.ODataV4Dto;
import com.latihan.java.spring.bc365.dto.SampleUserListDto;

public interface SampleUserListService {

    ODataV4Dto<SampleUserListDto> findAll();

    void save(SampleUserListDto request);
}
