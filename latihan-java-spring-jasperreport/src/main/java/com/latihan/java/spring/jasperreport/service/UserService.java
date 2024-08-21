package com.latihan.java.spring.jasperreport.service;

import com.latihan.java.spring.jasperreport.dto.CreateUserRequest;
import com.latihan.java.spring.jasperreport.dto.UserDto;
import org.springframework.core.io.Resource;

import java.util.List;

public interface UserService {

    String save(CreateUserRequest request);

    List<UserDto> getAll();

    Resource generatePdf();

    Resource generateExcel();

}
