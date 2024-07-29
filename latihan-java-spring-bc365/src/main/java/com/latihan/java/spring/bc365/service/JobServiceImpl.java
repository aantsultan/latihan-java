package com.latihan.java.spring.bc365.service;

import com.latihan.java.spring.bc365.dao.JobDao;
import com.latihan.java.spring.bc365.dto.JobDto;
import com.latihan.java.spring.bc365.dto.ValueDto;
import com.latihan.java.spring.bc365.model.Job;
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
    public JobDto jobList() {
        JobDto jobDto = new JobDto();
        Job job = jobDao.get();
        List<ValueDto> valueDtos = job.getValue().stream()
                .map(data -> {
                    ValueDto dto = new ValueDto();
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
        jobDto.setOdataContext(job.getOdataContext());
        jobDto.setValue(valueDtos);
        return jobDto;
    }
}
