package com.latihan.java.spring.bc365.dao;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.latihan.java.spring.bc365.aop.LoggingApi;
import com.latihan.java.spring.bc365.builder.HeaderBuilder;
import com.latihan.java.spring.bc365.helper.ApiHelper;
import com.latihan.java.spring.bc365.helper.ApplicationProperties;
import com.latihan.java.spring.bc365.model.ODataV4;
import com.latihan.java.spring.bc365.model.ODataV4SULResponse;
import com.latihan.java.spring.bc365.model.SampleUserList;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Repository
@RequiredArgsConstructor
@Slf4j
public class SampleUserListDaoImpl implements SampleUserListDao {

    private final RestTemplate sslRestTemplate;
    private final HeaderBuilder headerBuilder;
    private final ApplicationProperties properties;
    private final ObjectMapper objectMapper;

    @Override
    @LoggingApi
    public ODataV4<SampleUserList> findAll() {
        ResponseEntity<ODataV4<SampleUserList>> result = sslRestTemplate
                .exchange(properties.getBaseUrl() + properties.getUrlSampleUserList(), HttpMethod.GET,
                        headerBuilder.createBc365GetHeader(),
                        new ParameterizedTypeReference<ODataV4<SampleUserList>>() {
                        });
        return result.getBody();
    }

    @Override
    @LoggingApi
    public ODataV4SULResponse findByUid(Integer uid) {
        String uidStr = ApiHelper.createQueryUid(uid);
        ResponseEntity<ODataV4SULResponse> result = sslRestTemplate
                .exchange(properties.getBaseUrl() + properties.getUrlSampleUserList() + uidStr, HttpMethod.GET,
                        headerBuilder.createBc365GetHeader(),
                        ODataV4SULResponse.class);
        return result.getBody();
    }

    @Override
    @LoggingApi
    @SneakyThrows
    public ODataV4SULResponse create(SampleUserList request) {
        String body = objectMapper.writeValueAsString(request);
        HttpEntity<String> entity = new HttpEntity<>(body, headerBuilder.createBc365PostHeader().getHeaders());
        ResponseEntity<ODataV4SULResponse> result = sslRestTemplate
                .exchange(properties.getBaseUrl() + properties.getUrlSampleUserList(), HttpMethod.POST, entity,
                        ODataV4SULResponse.class);
        return result.getBody();
    }

    @Override
    @LoggingApi
    @SneakyThrows
    public ODataV4SULResponse update(SampleUserList request) {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String body = objectMapper.writeValueAsString(request);
        MultiValueMap<String, String> map = new HttpHeaders();
        map.add(HttpHeaders.IF_MATCH, request.getOdataEtag());
        HttpEntity<String> entity = headerBuilder.createBc365PostHeader(body, map);
        String uid = ApiHelper.createQueryUid(request.getUid());
        ResponseEntity<ODataV4SULResponse> result = sslRestTemplate
                .exchange(properties.getBaseUrl() + properties.getUrlSampleUserList() + uid, HttpMethod.PATCH,
                        entity, ODataV4SULResponse.class);
        return result.getBody();
    }

    @Override
    @SneakyThrows
    public void delete(SampleUserList request) {
        String uid = ApiHelper.createQueryUid(request.getUid());
        MultiValueMap<String, String> map = new HttpHeaders();
        map.add(HttpHeaders.IF_MATCH, request.getOdataEtag());
        HttpEntity<String> entity = headerBuilder.createBc365PostHeader(map);
        sslRestTemplate
                .exchange(properties.getBaseUrl() + properties.getUrlSampleUserList() + uid, HttpMethod.DELETE,
                        entity, String.class);
    }
}
