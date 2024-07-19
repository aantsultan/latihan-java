package com.latihan.java.restfulapi.flutter.config.jwt;

import com.latihan.java.restfulapi.flutter.model.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface JwtTokenProvider {

    String generateJwtToken(UserPrincipal userPrincipal);
    List<GrantedAuthority> getAuthorities(String token);
    Authentication getAuthentication(String phoneNumber, List<GrantedAuthority> authorities, HttpServletRequest request);
    boolean isTokenValid(String username, String token);
    String getSubject(String token);
}
