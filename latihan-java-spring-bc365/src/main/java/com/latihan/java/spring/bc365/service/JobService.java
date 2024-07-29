package com.latihan.java.spring.bc365.service;

import com.latihan.java.spring.bc365.dto.JobDto;
import com.latihan.java.spring.bc365.dto.ODataV4Dto;

public interface JobService {

    ODataV4Dto<JobDto> jobList();

}
