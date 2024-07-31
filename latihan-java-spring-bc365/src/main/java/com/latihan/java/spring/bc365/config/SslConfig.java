package com.latihan.java.spring.bc365.config;

import com.latihan.java.spring.bc365.helper.ApplicationProperties;
import lombok.RequiredArgsConstructor;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;


@Configuration
@RequiredArgsConstructor
public class SslConfig {

    private final ApplicationProperties properties;

    @Bean
    public RestTemplate sslRestTemplate() throws IOException, CertificateException, NoSuchAlgorithmException,
            KeyStoreException, KeyManagementException {
        SSLContext sslContext = new SSLContextBuilder()
                .loadTrustMaterial(properties.getTrustStore().getURL(), properties.getSslPassword().toCharArray()).build();
        SSLConnectionSocketFactory sslConFactory = new SSLConnectionSocketFactory(sslContext);
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslConFactory).build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        return new RestTemplate(requestFactory);
    }

    @Bean
    public HttpEntity<String> sslBc365GetJson(){
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_JSON);
        HttpHeaders header = new HttpHeaders();
        header.setBasicAuth(properties.getBc365username(), properties.getBc365password());
        header.setAccept(mediaTypes);
        return new HttpEntity<>(header);
    }

    @Bean
    public HttpEntity<String> sslBc365PostJson(){
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_JSON);
        HttpHeaders header = new HttpHeaders();
        header.setBasicAuth(properties.getBc365username(), properties.getBc365password());
        header.setAccept(mediaTypes);
        header.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(header);
    }

}
