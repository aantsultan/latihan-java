package com.latihan.java.multiple.database.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.latihan.java.multiple.database.dto.UserSalesGroupDto;
import com.latihan.java.multiple.database.dto.md.SalesGroupDto;
import com.latihan.java.multiple.database.service.SalesGroupService;
import com.latihan.java.multiple.database.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserSalesGroupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private SalesGroupService salesGroupService;

    @BeforeEach
    void setUp() {
        salesGroupService.deleteAll();
        userService.deleteAll();
    }

    @Test
    void save() throws Exception {
        SalesGroupDto salesGroupDto = new SalesGroupDto();
        salesGroupDto.setName("SG 1");

        UserSalesGroupDto request = new UserSalesGroupDto();
        request.setName("Aant");
        request.setEmail("sultan@mail.com");
        request.setSalesGroup(salesGroupDto);

        mockMvc.perform(
                post("/api/user-salesgroup")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            String contentAsString = result.getResponse().getContentAsString();
            Assertions.assertEquals("Success save user salesgroup", contentAsString);
        });
    }

    @Test
    void saveUserFailed() throws Exception {
        SalesGroupDto salesGroupDto = new SalesGroupDto();
        salesGroupDto.setName("SG 1");

        UserSalesGroupDto request = new UserSalesGroupDto();
        request.setName("Aant");
        request.setSalesGroup(salesGroupDto);

        mockMvc.perform(
                post("/api/user-salesgroup")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isInternalServerError()
        );
    }

}
