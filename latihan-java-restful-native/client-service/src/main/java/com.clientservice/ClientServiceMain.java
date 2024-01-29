package com.clientservice;

import com.common.util.dto.CommonResponseDto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

@SpringBootApplication
public class ClientServiceMain {

    public static void main(String[] args) {
        SpringApplication.run(ClientServiceMain.class, args);
    }

}
