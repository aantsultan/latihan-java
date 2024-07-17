package com.latihan.java.restfulapi.flutter.service;

import com.google.gson.Gson;
import com.latihan.java.restfulapi.flutter.dto.request.CreateUserRequest;
import com.latihan.java.restfulapi.flutter.dto.request.LoginRequest;
import com.latihan.java.restfulapi.flutter.dto.response.TokenResponse;
import com.latihan.java.restfulapi.flutter.dto.response.UserResponse;
import com.latihan.java.restfulapi.flutter.dto.response.WebResponse;
import com.latihan.java.restfulapi.flutter.model.User;
import com.latihan.java.restfulapi.flutter.model.UserPrincipal;
import com.latihan.java.restfulapi.flutter.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@Qualifier("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ValidatorService validatorService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private Gson gson;

    @Override
    public WebResponse<String> create(CreateUserRequest request) {
        log.info("Request UserServiceImpl {}", gson.toJson(request));
        validatorService.validate(request);
        this.validate(request);

        User user = new User();
        user.setFullName(request.getFullName());
        user.setBirthDate(request.getBirthDate());
        user.setEmail(request.getEmail());
        user.setGender(request.getGender());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPromoEvents(request.getPromoEvents());
        user.setTermConditions(request.getTermConditions());
        repository.save(user);
        log.info("Response UserServiceImpl {}", gson.toJson(user));
        return WebResponse.<String>builder()
                .data("OK").build();
    }

    @Override
    public WebResponse<TokenResponse> auth(LoginRequest request) {
        log.info("Request auth : {}", gson.toJson(request));
        validatorService.validate(request);

        User user = repository.findByPhoneNumber(request.getPhoneNumber())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password is incorrect"));

        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            user.setToken(UUID.randomUUID().toString());
            user.setTokenExpiredAt(System.currentTimeMillis() + (1000L * 60 * 60 * 24 * 30));
            repository.save(user);
            log.info("Response auth : {}", gson.toJson(user));
            return WebResponse.<TokenResponse>builder()
                    .data(TokenResponse.builder()
                            .token(user.getToken())
                            .expiredAt(user.getTokenExpiredAt())
                            .build())
                    .build();
        }
        log.error("Response auth : Username or password is incorrect");
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password is incorrect");
    }

    @Override
    public WebResponse<UserResponse> get() {
        User user = repository.findByToken(httpServletRequest.getHeader("X-API-TOKEN"))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized"));
        UserResponse result = UserResponse.builder()
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .birthDate(user.getBirthDate())
                .gender(user.getGender())
                .promoEvents(user.getPromoEvents())
                .termConditions(user.getTermConditions())
                .build();

        return WebResponse.<UserResponse>builder()
                .data(result)
                .build();
    }

    @Override
    public void validate(CreateUserRequest request) {
        if (!request.getConfirmPassword().equalsIgnoreCase(request.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password and Confirmation Password is not match");
        }

        Optional<User> opt = repository.findByPhoneNumber(request.getPhoneNumber());
        if (opt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exist");
        }

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optional = repository.findByPhoneNumber(username);
        if (!optional.isPresent()) {
            log.error("User not found by username: " + username);
            throw new UsernameNotFoundException("User not found by username: " + username);
        }
        User user = optional.get();

        UserPrincipal userPrincipal = new UserPrincipal(user);
        log.info("Returning found user by username: " + username);

        return userPrincipal;
    }
}
