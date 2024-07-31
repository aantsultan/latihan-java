package com.latihan.java.spring.bc365.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class LoggingApiAop {

    private final ObjectMapper objectMapper;

    @Around("@annotation(LoggingApi)")
    public Object loggingApi(ProceedingJoinPoint joinPoint) throws Throwable {
        String uuid = UUID.randomUUID().toString();
        String simpleName = joinPoint.getTarget().getClass().getSimpleName();
        String method = joinPoint.getSignature().getName();
        try {
            Object request = joinPoint.getArgs().length == 0 ? null : joinPoint.getArgs()[0];
            log.info("{}.{} | REQUEST [{}] : {}", simpleName, method, uuid, objectMapper.writeValueAsString(request));
            Object result = joinPoint.proceed();
            log.info("{}.{} | RESPONSE [{}] : {}", simpleName, method, uuid, objectMapper.writeValueAsString(result));
            return result;
        } catch (Exception e) {
            log.error("{}.{} : RESPONSE [{}] : {}", simpleName, method, uuid, e.getMessage(), e);
            throw e;
        }
    }

}
