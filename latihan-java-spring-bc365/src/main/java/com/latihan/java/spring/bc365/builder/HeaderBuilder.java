package com.latihan.java.spring.bc365.builder;

import com.latihan.java.spring.bc365.helper.ApplicationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class HeaderBuilder {

    private final ApplicationProperties properties;

    public HttpEntity<String> createBc365GetHeader() {
        List<MediaType> mediaTypes = new ArrayList<>();
        HttpHeaders header = new HttpHeaders();
        mediaTypes.add(MediaType.APPLICATION_JSON);
        header.setBasicAuth(properties.getBc365username(), properties.getBc365password());
        header.setAccept(mediaTypes);
        return new HttpEntity<>(header);
    }

    public HttpEntity<String> createBc365PostHeader() {
        return this.createBc365PostHeader(null);
    }

    public HttpEntity<String> createBc365PostHeader(MultiValueMap<String, String> requestHeader) {
        return this.createBc365PostHeader(null, requestHeader);
    }

    public HttpEntity<String> createBc365PostHeader(@Nullable String body, @Nullable MultiValueMap<String, String> requestHeader) {
        MultiValueMap<String, String> header = new HttpHeaders();
        String encoded = HttpHeaders.encodeBasicAuth(properties.getBc365username(), properties.getBc365password(), null);
        header.add(HttpHeaders.AUTHORIZATION, "Basic " + encoded);
        header.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        header.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        if (requestHeader != null) {
            header.addAll(requestHeader);
        }

        if (body == null) {
            return new HttpEntity<>(header);
        } else {
            return new HttpEntity<>(body, header);
        }
    }

}
