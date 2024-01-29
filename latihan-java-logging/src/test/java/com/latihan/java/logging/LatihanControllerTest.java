package com.latihan.java.logging;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@SpringBootTest
@WebAppConfiguration
public class LatihanControllerTest {

    private MockMvc mockMvc;
    private String jsonString;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() throws FileNotFoundException {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        File file = ResourceUtils.getFile("classpath:save-warping.json");
        StringBuilder jsonBuilder = new StringBuilder();
        if (file.exists()) {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                jsonBuilder.append(myReader.nextLine());
            }
            myReader.close();
        }
        this.jsonString = jsonBuilder.toString();
    }

    @RepeatedTest(1000)
    void latihanTest() throws Exception {
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/latihan")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(this.jsonString)).andReturn();

        String resultString = result.getResponse().getContentAsString();
        Assertions.assertEquals(this.jsonString, resultString);
    }

}
