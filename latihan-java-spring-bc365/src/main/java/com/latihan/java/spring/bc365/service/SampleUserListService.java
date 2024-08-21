package com.latihan.java.spring.bc365.service;

import com.latihan.java.spring.bc365.dto.ODataV4Dto;
import com.latihan.java.spring.bc365.dto.sampleuserlist.CreateSULDto;
import com.latihan.java.spring.bc365.dto.sampleuserlist.SampleUserListDto;
import com.latihan.java.spring.bc365.dto.sampleuserlist.UpdateSULDto;

public interface SampleUserListService {

    ODataV4Dto<SampleUserListDto> findAll();

    void save(CreateSULDto request);

    void update(UpdateSULDto request, Integer uid);

    SampleUserListDto findByUid(Integer uid);

    void deleteByUid(Integer uid);
}
