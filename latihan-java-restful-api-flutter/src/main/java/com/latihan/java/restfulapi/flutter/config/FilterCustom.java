package com.latihan.java.restfulapi.flutter.config;

import com.latihan.java.restfulapi.flutter.model.User;
import com.latihan.java.restfulapi.flutter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.latihan.java.restfulapi.flutter.helper.WebConstant.WHITELIST_URL;

@Component
@Transactional
public class FilterCustom extends OncePerRequestFilter {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String urlRequest = request.getRequestURI();
        List<String> urls = Arrays.asList(WHITELIST_URL);
        boolean isNotWhitelistUrl = urls.stream().noneMatch(data -> data.equalsIgnoreCase(urlRequest));
        if (isNotWhitelistUrl) {
            String token = request.getHeader("X-API-TOKEN");
            if (token == null) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                filterChain.doFilter(request, response);
                return;
            }
            Optional<User> opt = userRepository.findByToken(token);
            if (!opt.isPresent()) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                filterChain.doFilter(request, response);
                return;
            }
            if (opt.get().getTokenExpiredAt() < System.currentTimeMillis()) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                filterChain.doFilter(request, response);
                return;
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
