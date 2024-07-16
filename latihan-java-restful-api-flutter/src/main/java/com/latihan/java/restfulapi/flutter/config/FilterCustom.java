package com.latihan.java.restfulapi.flutter.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.latihan.java.restfulapi.flutter.dto.response.WebResponse;
import com.latihan.java.restfulapi.flutter.model.User;
import com.latihan.java.restfulapi.flutter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.latihan.java.restfulapi.flutter.helper.WebConstant.WHITELIST_URL;

@Component
@Transactional
public class FilterCustom extends OncePerRequestFilter {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String urlRequest = request.getRequestURI();
        List<String> urls = Arrays.asList(WHITELIST_URL);
        boolean isNotWhitelistUrl = urls.stream().noneMatch(data -> data.equalsIgnoreCase(urlRequest));
        if (isNotWhitelistUrl) {
            String token = request.getHeader("X-API-TOKEN");
            if (token == null) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username and password is incorrect");
            }
            Optional<User> opt = userRepository.findByToken(token);
            if (!opt.isPresent()) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username and password is incorrect");
            }
            if (opt.get().getTokenExpiredAt() < System.currentTimeMillis()) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username and password is incorrect");
            }

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(opt.get().getPhoneNumber(), null, null);
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            User user = opt.get();
            user.setTokenExpiredAt(System.currentTimeMillis() + (1000L * 60 * 60 * 24 * 30));
            userRepository.save(user);
        }
        filterChain.doFilter(request, response);
    }
}
