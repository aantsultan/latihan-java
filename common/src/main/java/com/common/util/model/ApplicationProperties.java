package com.common.util.model;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Configuration
@PropertySource("classpath:util.properties")
public class ApplicationProperties {

    @Value("${controller.util.keyname}")
    String keyName;
}
