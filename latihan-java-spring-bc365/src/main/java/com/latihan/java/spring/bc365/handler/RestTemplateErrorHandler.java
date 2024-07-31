package com.latihan.java.spring.bc365.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.latihan.java.spring.bc365.exception.WebException;
import com.latihan.java.spring.bc365.model.error.ODataV4Error;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class RestTemplateErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().is5xxServerError() || response.getStatusCode().is4xxClientError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().is4xxClientError()) {
            StringBuilder textBuilder = new StringBuilder();
            try (Reader reader = new BufferedReader(new InputStreamReader
                    (response.getBody(), StandardCharsets.UTF_8))) {
                int c;
                while ((c = reader.read()) != -1) {
                    textBuilder.append((char) c);
                }
            }
            String errorMessage = textBuilder.toString();
            String message;
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                ODataV4Error error = objectMapper.readValue(errorMessage, ODataV4Error.class);
                message = error.getError().getMessage();
            } catch (Exception e) {
                message = e.getMessage();
            }
            throw new WebException(message);
        }
    }
}
