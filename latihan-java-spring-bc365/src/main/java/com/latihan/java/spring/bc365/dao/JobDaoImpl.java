package com.latihan.java.spring.bc365.dao;

import com.latihan.java.spring.bc365.aop.LoggingApi;
import com.latihan.java.spring.bc365.builder.HeaderBuilder;
import com.latihan.java.spring.bc365.helper.ApplicationProperties;
import com.latihan.java.spring.bc365.model.Job;
import com.latihan.java.spring.bc365.model.ODataV4;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
@Slf4j
@RequiredArgsConstructor
public class JobDaoImpl implements JobDao {

    private final RestTemplate sslRestTemplate;

    private final HeaderBuilder headerBuilder;

    private final ApplicationProperties properties;

    @Override
    @LoggingApi
    public ODataV4<Job> get() {
        ResponseEntity<ODataV4<Job>> result = sslRestTemplate
                .exchange(properties.getBaseUrl() + properties.getJobListUrl(), HttpMethod.GET,
                        headerBuilder.createBc365GetHeader(),
                        new ParameterizedTypeReference<ODataV4<Job>>() {
                        });
        return result.getBody();
    }
}
