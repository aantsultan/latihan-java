package com.latihan.java.spring.bc365.service;

import com.latihan.java.spring.bc365.dao.JobDao;
import com.latihan.java.spring.bc365.dto.JobDto;
import com.latihan.java.spring.bc365.dto.ODataV4Dto;
import com.latihan.java.spring.bc365.model.Job;
import com.latihan.java.spring.bc365.model.ODataV4;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobDao jobDao;

    @Override
    public ODataV4Dto<JobDto> jobList() {
        ODataV4<Job> response = jobDao.get();
        List<JobDto> jobDtos = response.getValue().stream()
                .map(data -> {
                    JobDto dto = new JobDto();
                    dto.setNo(data.getNo());
                    dto.setDescription(data.getDescription());
                    dto.setStatus(data.getStatus());
                    dto.setOdataEtag(data.getOdataEtag());
                    dto.setPersonResponsible(data.getPersonResponsible());
                    dto.setProjectManager(data.getProjectManager());
                    dto.setSearchDescription(data.getSearchDescription());
                    dto.setBillToCustomerNo(data.getBillToCustomerNo());
                    return dto;
                }).collect(Collectors.toList());
        return ODataV4Dto.<JobDto>builder()
                .odataContext(response.getOdataContext())
                .value(jobDtos)
                .build();
    }
}
