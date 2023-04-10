package com.clientservice;

import com.common.util.dto.CommonResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class ClientServiceTest {

    @Test
    void helloWorld(){
        doRestfulAPI_V1();
    }

    static void doRestfulAPI_V1() {
        RestTemplate restTemplate = new RestTemplate();
        HashMap<String, Object> request = new HashMap<>();
        request.put("page", 0);
        request.put("total", 3);
        ResponseEntity<CommonResponseDto> responseEntity =
                restTemplate.getForEntity(
                        "http://localhost:8200/product/get/page/{page}/total/{total}",
                        CommonResponseDto.class,
                        request
                );
        System.out.println(responseEntity.getBody().getStatus());
    }

    static void doRestfulAPI_V2(int page, int total) throws IOException {
        URL url = new URL("http://localhost:8200/product/get/page/" + page + "/total/" + total);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        int resCode = httpURLConnection.getResponseCode();
        if (resCode == HttpURLConnection.HTTP_OK) {
            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String inputLine;
            StringBuffer response = new StringBuffer();
            inputLine = bufferedReader.readLine();
            while (inputLine != null) {
                response.append(inputLine);
                inputLine = bufferedReader.readLine();
            }
            bufferedReader.close();
            System.out.println("response : " + response.toString());
        } else {
            System.out.println("CONNECTION ERROR");
        }

    }
}
