package com.latihan.java.multiple.database.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.latihan.java.multiple.database.dto.hts.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.ObjectUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void findAll() throws Exception {
        mockMvc.perform(
                get("/api/users")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            String contentAsString = result.getResponse().getContentAsString();
            if (ObjectUtils.isEmpty(contentAsString)) {
                assertEquals("", contentAsString);
            } else {
                List<UserDto> response = objectMapper.readValue(contentAsString
                        , new TypeReference<>() {
                        });
                assertTrue(response.isEmpty());
            }
        });
    }

}
