package com.latihan.java.spring.jasperreport.controller;

import com.latihan.java.spring.jasperreport.dto.CreateUserRequest;
import com.latihan.java.spring.jasperreport.dto.UserDto;
import com.latihan.java.spring.jasperreport.dto.WebResponse;
import com.latihan.java.spring.jasperreport.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;
import static org.springframework.http.MediaType.APPLICATION_PDF;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public WebResponse<String> save(@RequestBody CreateUserRequest request) {
        String result = userService.save(request);
        return WebResponse.<String>builder().data(result).build();
    }

    @GetMapping
    public WebResponse<List<UserDto>> getAll() {
        List<UserDto> result = userService.getAll();
        return WebResponse.<List<UserDto>>builder()
                .data(result).build();
    }

    @GetMapping(path = "/pdf")
    public ResponseEntity<Resource> pdf() {
        Resource result = userService.generatePdf();
        return ResponseEntity.ok()
                .header(CONTENT_DISPOSITION, "attachment; filename=" + "user_report"
                        + System.currentTimeMillis() + ".pdf")
                .contentType(APPLICATION_PDF)
                .body(result);
    }

    @GetMapping(path = "/excel")
    public ResponseEntity<Resource> excel() {
        Resource result = userService.generateExcel();
        return ResponseEntity.ok()
                .header(CONTENT_DISPOSITION, "attachment; filename=" + "user_report"
                        + System.currentTimeMillis() + ".xlsx")
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(result);
    }

}
