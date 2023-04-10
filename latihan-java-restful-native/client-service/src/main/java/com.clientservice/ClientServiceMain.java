package com.clientservice;

import com.common.util.dto.CommonResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

@SpringBootApplication
public class ClientServiceMain implements CommandLineRunner {

    public static void main (String[] args){
        SpringApplication.run(ClientServiceMain.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        doRestfulAPI_V1();
    }

    static void doRestfulAPI_V1(){
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

    static void doRestfulAPI_V2() throws MalformedURLException {
        URL url = new URL("http://localhost:8200/product/get/page/{page}/total/{total}");

    }
}

class JavaScriptEngine{

    void doTask(){
        System.out.println("hello world");
    }

}
