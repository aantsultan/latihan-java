package com.latihan.java.spring.mongodb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.latihan.java.spring.mongodb.dto.CustomerDto;
import com.latihan.java.spring.mongodb.dto.ResponseDto;
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
public class CustomerControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void getTest() throws Exception {
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders
                .get("/customer")).andReturn();

        String resultString = result.getResponse().getContentAsString();
        ResponseDto dto = mapper.readValue(resultString, ResponseDto.class);
        Assertions.assertNotEquals(0, dto.getData().size());
        Assertions.assertEquals("Aant", dto.getData().get(0).getFirstName());
    }

    @Test
    void saveTest() throws Exception {
        String id = String.valueOf(System.currentTimeMillis());
        String firstName = "Aant";
        String lastName = "Sultan";
        CustomerDto customerDto = CustomerDto.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .build();

        String request = mapper.writeValueAsString(customerDto);

        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders
                .post("/customer")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(request)).andReturn();

        String httpStatus = result.getResponse().getContentAsString();
        Assertions.assertEquals(HttpStatus.OK.name(), httpStatus);
    }

}
