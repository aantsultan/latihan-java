package com.clientservice;

import com.common.util.dto.CommonResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.script.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class ClientServiceTest {

    @Test
    void doRestfulAPI() throws IOException {
//        doRestfulAPI_V1();
        System.out.println("response : " + doRestfulAPI_V2(0, 10, Integer.MAX_VALUE));
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

    static int doRestfulAPI_V2(int page, int total, int initRepeat) throws IOException {
        if (initRepeat == 0) {
            return 0;
        } else {
            try {
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
                    String result = response.toString();
                    return countQuantity(result) + doRestfulAPI_V2(page + 1, total, countData(result));

                } else {
                    throw new IOException("CONNECTION ERROR");
                }
            } catch (IOException ie) {
                throw new IOException("ERROR");
            }
        }
    }

    static int countData(String input) {
        try {
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
            Bindings bindings = engine.getContext().getBindings(ScriptContext.GLOBAL_SCOPE);
            bindings.put("input", input);
            return (Integer) engine.eval(
                    "JSON.parse('" + input + "').data.product.length");
        } catch (ScriptException | ClassCastException se) {
            throw new RuntimeException("ERROR COUNT_DATE");
        }
    }

    static int countQuantity(String input) {
        try {
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
            Bindings bindings = engine.getContext().getBindings(ScriptContext.GLOBAL_SCOPE);
            bindings.put("input", input);
            int page = (Integer) engine.eval(
                    "JSON.parse('" + input + "').data.product.length");
            int count = 0;
            for (int i = 0; i < page; i++) {
                count += (Integer) engine.eval(
                        "JSON.parse('" + input + "').data.product[" + i + "].productQuantity");
            }
            return count;
        } catch (ScriptException | ClassCastException se) {
            throw new RuntimeException("ERROR COUNT_QUANTITY");
        }
    }
}
