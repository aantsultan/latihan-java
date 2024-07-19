package com.latihan.java.restfulapi.flutter.config;

import com.latihan.java.restfulapi.flutter.model.User;
import com.latihan.java.restfulapi.flutter.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
@Slf4j
@Transactional
public class FilterCustom extends OncePerRequestFilter {

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorization != null && authorization.startsWith("Bearer ")) {

            Optional<User> opt = userService.findByToken(authorization.substring(7));
            if (!opt.isPresent()) {
                filterChain.doFilter(request, response);
                return;
            }
            if (opt.get().getTokenExpiredAt() < System.currentTimeMillis()) {
                filterChain.doFilter(request, response);
                return;
            }
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            Authentication authentication = userService.authenticate(opt.get(), request);
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);
            userService.extendExpiredToken(opt.get());
        }
        filterChain.doFilter(request, response);
    }
}
