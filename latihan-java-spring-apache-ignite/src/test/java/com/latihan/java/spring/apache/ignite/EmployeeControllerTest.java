package com.latihan.java.spring.apache.ignite;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.latihan.java.spring.apache.ignite.dto.EmployeeDto;
import com.latihan.java.spring.apache.ignite.dto.ResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@WebAppConfiguration
class EmployeeControllerTest {

    private MockMvc mockMvc;
    private final WebApplicationContext webApplicationContext;
    private final ObjectMapper mapper;

    private EmployeeDto dto;

    private String dtoString;

    @Autowired
    public EmployeeControllerTest(WebApplicationContext webApplicationContext, ObjectMapper mapper) {
        this.webApplicationContext = webApplicationContext;
        this.mapper = mapper;
    }

    @BeforeEach
    void setUp() throws JsonProcessingException {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        this.dto = EmployeeDto.builder()
                .id(1000)
                .name("Elaina")
                .isEmployed(true)
                .build();

        this.dtoString = mapper.writeValueAsString(this.dto);
    }

    @Test
    void saveTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/employee")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(this.dtoString)).andReturn();

        String result = mvcResult.getResponse().getContentAsString();
        int httpStatus = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(HttpStatus.OK.value(), httpStatus);
    }

    @Test
    void getTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/employee/{id}", this.dto.getId())).andReturn();

        String result = mvcResult.getResponse().getContentAsString();
        ResponseDto responseDto = mapper.readValue(result, ResponseDto.class);

        Assertions.assertEquals(this.dto, responseDto.getData());
    }
}
