package com.latihan.java.spring.bc365.dao;

import com.latihan.java.spring.bc365.helper.ApplicationProperties;
import com.latihan.java.spring.bc365.model.ODataV4;
import com.latihan.java.spring.bc365.model.SampleUserList;
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
@RequiredArgsConstructor
@Slf4j
public class SampleUserListDaoImpl implements SampleUserListDao {

    private final RestTemplate sslRestTemplate;

    private final HttpEntity<String> sslBc365Json;

    private final ApplicationProperties properties;

    @Override
    public ODataV4<SampleUserList> findAll() {
        try {
            ResponseEntity<ODataV4<SampleUserList>> result = sslRestTemplate
                    .exchange(properties.getBaseUrl() + properties.getUrlSampleUserList(), HttpMethod.GET, sslBc365Json,
                            new ParameterizedTypeReference<ODataV4<SampleUserList>>() {
                            });
            return result.getBody();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Failed to get data");
        }
    }
}
