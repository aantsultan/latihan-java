package com.latihan.java.restfulapi.flutter.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.latihan.java.restfulapi.flutter.dto.response.WebResponse;
import com.latihan.java.restfulapi.flutter.helper.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class EntryPointCustom extends Http403ForbiddenEntryPoint {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException arg2) throws IOException {
        if (response.getStatus() == HttpStatus.UNAUTHORIZED.value()) {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(objectMapper.writeValueAsBytes(WebResponse.<String>builder()
                    .errors(ErrorEnum.ERROR_401.getValue())
                    .build()));
            outputStream.flush();
        }
    }

}
