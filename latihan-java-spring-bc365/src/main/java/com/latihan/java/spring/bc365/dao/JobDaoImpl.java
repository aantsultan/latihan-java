package com.latihan.java.spring.bc365.dao;

import com.latihan.java.spring.bc365.model.Job;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Repository
@Slf4j
@RequiredArgsConstructor
public class JobDaoImpl implements JobDao {

    @Value("${bc365.web.service.odatav4.base}")
    private String baseUrl;

    @Value("${bc365.web.service.odatav4.job.list}")
    private String jobListUrl;

    private final RestTemplate sslRestTemplate;

    private final HttpEntity<String> sslBc365Json;

    @Override
    public Job get() {
        try {
            ResponseEntity<Job> result = sslRestTemplate.exchange(baseUrl + jobListUrl, HttpMethod.GET, sslBc365Json, Job.class);
            return result.getBody();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Failed to get data");
        }
    }
}
