package com.latihan.java.spring.bc365.helper;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ApplicationProperties {

    @Value("${bc365.web.service.odatav4.base}")
    private String baseUrl;

    @Value("${bc365.web.service.odatav4.job.list}")
    private String jobListUrl;

    @Value("${trust.store}")
    private Resource trustStore;

    @Value("${trust.store.password}")
    private String sslPassword;

    @Value("${bc365.web.service.odatav4.username}")
    private String bc365username;

    @Value("${bc365.web.service.odatav4.password}")
    private String bc365password;

    @Value("${bc365.web.service.odatav4.sample.list.user}")
    private String urlSampleUserList;

}
