package com.latihan.java.spring.oauth2.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping(path = "/api/users")
    public String get() {
        return "Hello Users";
    }

    @Secured("ROLE_salah")
    @DeleteMapping(path = "/api/users/{id}")
    public String delete(@PathVariable String id) {
        return "Delete Id : " + id;
    }

    @PreAuthorize("hasAuthority('ROLE_salah') or #id == #jwt.subject")
    @GetMapping(path = "/api/users/{id}")
    public String getById(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        return "Get Id : " + id + ", and get JWT : " + jwt.getSubject();
    }

}
