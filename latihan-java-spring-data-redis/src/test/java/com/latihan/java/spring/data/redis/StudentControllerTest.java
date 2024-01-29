package com.latihan.java.spring.data.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.latihan.java.spring.data.redis.dto.ResponseDto;
import com.latihan.java.spring.data.redis.dto.StudentDto;
import com.latihan.java.spring.data.redis.util.Gender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@WebAppConfiguration
class StudentControllerTest {

    @Autowired
    private ObjectMapper mapper;
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    private StudentDto request;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        this.request = StudentDto.builder()
                .id("STUDENT01")
                .name("AANT")
                .grade(100)
                .gender(Gender.MALE)
                .build();
    }

    @Test
    void getTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/student")).andReturn();
        String json = result.getResponse().getContentAsString();
        ResponseDto dto = mapper.readValue(json, ResponseDto.class);
        Assertions.assertEquals(0, dto.getData().size());
    }

    @Test
    void saveTest() throws Exception {

        String requestJson = mapper.writeValueAsString(this.request);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post("/student")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestJson)).andReturn();
        String json = result.getResponse().getContentAsString();
        Assertions.assertEquals("SUKSES", json);
    }

    @Test
    void findByIdTest() throws Exception {
        String id = "STUDENT01";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/student/{id}", id)).andReturn();
        String json = result.getResponse().getContentAsString();
        StudentDto dto = mapper.readValue(json, StudentDto.class);
        int status = result.getResponse().getStatus();

        Assertions.assertEquals(200, status);
        Assertions.assertEquals(dto, this.request);
    }

}
