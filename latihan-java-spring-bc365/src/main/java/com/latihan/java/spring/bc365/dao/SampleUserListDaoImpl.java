package com.latihan.java.spring.bc365.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.latihan.java.spring.bc365.aop.LoggingApi;
import com.latihan.java.spring.bc365.helper.ApplicationProperties;
import com.latihan.java.spring.bc365.model.ODataV4;
import com.latihan.java.spring.bc365.model.ODataV4SULResponse;
import com.latihan.java.spring.bc365.model.SampleUserList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Repository
@RequiredArgsConstructor
@Slf4j
public class SampleUserListDaoImpl implements SampleUserListDao {

    private final RestTemplate sslRestTemplate;
    private final HttpEntity<String> sslBc365GetJson;
    private final HttpEntity<String> sslBc365PostJson;
    private final ApplicationProperties properties;
    private final ObjectMapper objectMapper;

    @Override
    @LoggingApi
    public ODataV4<SampleUserList> findAll() {
        try {
            ResponseEntity<ODataV4<SampleUserList>> result = sslRestTemplate
                    .exchange(properties.getBaseUrl() + properties.getUrlSampleUserList(), HttpMethod.GET, sslBc365GetJson,
                            new ParameterizedTypeReference<ODataV4<SampleUserList>>() {
                            });
            return result.getBody();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Failed to save data");
        }
    }

    @Override
    @LoggingApi
    public ODataV4SULResponse create(SampleUserList request) {
        try {
            String body = objectMapper.writeValueAsString(request);
            HttpHeaders headers = sslBc365PostJson.getHeaders();
            HttpEntity<String> entity = new HttpEntity<>(body, headers);
            ResponseEntity<ODataV4SULResponse> result = sslRestTemplate
                    .exchange(properties.getBaseUrl() + properties.getUrlSampleUserList(), HttpMethod.POST, entity,
                            ODataV4SULResponse.class);
            return result.getBody();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Failed to get data");
        }
    }
}
