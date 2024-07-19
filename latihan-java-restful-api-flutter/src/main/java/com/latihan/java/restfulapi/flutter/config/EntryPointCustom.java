package com.latihan.java.restfulapi.flutter.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.latihan.java.restfulapi.flutter.dto.response.WebResponse;
import com.latihan.java.restfulapi.flutter.helper.ErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

@Component
@Slf4j
@Transactional
public class EntryPointCustom implements AuthenticationEntryPoint {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException arg2) throws IOException {
        createError(null, response);
    }

    private void createError(String token, HttpServletResponse response) throws IOException {
        String uuid = UUID.randomUUID().toString();
        if (token != null) log.error("REQUEST [{}] : token {}", uuid, token);
        WebResponse<String> error = WebResponse.<String>builder()
                .errors(ErrorEnum.ERROR_401.getValue())
                .build();
        String errorStr = objectMapper.writeValueAsString(error);
        if (token != null) log.error("RESPONSE [{}] : {}", uuid, errorStr);
        byte[] result = errorStr.getBytes();
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(result);
        outputStream.flush();
    }
}
