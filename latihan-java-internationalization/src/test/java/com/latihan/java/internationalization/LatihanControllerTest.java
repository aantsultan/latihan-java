package com.latihan.java.internationalization;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Locale;

@SpringBootTest
@WebAppConfiguration
public class LatihanControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void latihanTestID() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        Locale contentLanguage = new Locale("in", "ID");
        headers.setContentLanguage(contentLanguage);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post("/latihan")
                .contentType(MediaType.TEXT_PLAIN_VALUE)
                .content("AANT")
                .headers(headers)
        ).andReturn();

        String expected = "Halo, Selamat Datang AANT"; //--> ID

        String resultString = result.getResponse().getContentAsString();
        Assertions.assertEquals(expected, resultString);
    }

    @Test
    void latihanTestDefault() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        Locale contentLanguage = Locale.getDefault();
        headers.setContentLanguage(contentLanguage);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post("/latihan")
                .contentType(MediaType.TEXT_PLAIN_VALUE)
                .content("AANT")
                .headers(headers)
        ).andReturn();

        String expected = "Hello, Welcome AANT";

        String resultString = result.getResponse().getContentAsString();
        Assertions.assertEquals(expected, resultString);
    }

}
