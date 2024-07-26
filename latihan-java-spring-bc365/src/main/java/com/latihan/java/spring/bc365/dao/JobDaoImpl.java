package com.latihan.java.spring.bc365.dao;

import com.latihan.java.spring.bc365.model.Job;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Repository
@Slf4j
public class JobDaoImpl implements JobDao {

    @Value("${bc365.web.service.odatav4.job.list}")
    private String jobODataV4Url;

    @Override
    public Job get() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<Job> result = restTemplate.getForEntity(jobODataV4Url, Job.class);
            log.info("Job Result : {}", result);
            return result.getBody();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Failed to get data");
        }
    }
}
