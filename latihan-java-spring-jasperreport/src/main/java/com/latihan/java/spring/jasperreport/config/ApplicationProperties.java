package com.latihan.java.spring.jasperreport.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ApplicationProperties {

    @Value("${file.report.path}")
    private String reportPath;

}
