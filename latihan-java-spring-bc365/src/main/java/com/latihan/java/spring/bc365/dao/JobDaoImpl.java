package com.latihan.java.spring.bc365.dao;

import com.latihan.java.spring.bc365.aop.LoggingApi;
import com.latihan.java.spring.bc365.helper.ApplicationProperties;
import com.latihan.java.spring.bc365.model.Job;
import com.latihan.java.spring.bc365.model.ODataV4;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
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

    private final RestTemplate sslRestTemplate;

    private final HttpEntity<String> sslBc365GetJson;

    private final ApplicationProperties properties;

    @Override
    @LoggingApi
    public ODataV4<Job> get() {
        try {
            ResponseEntity<ODataV4<Job>> result = sslRestTemplate
                    .exchange(properties.getBaseUrl() + properties.getJobListUrl(), HttpMethod.GET, sslBc365GetJson,
                            new ParameterizedTypeReference<ODataV4<Job>>() {
                            });
            return result.getBody();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Failed to get data");
        }
    }
}
