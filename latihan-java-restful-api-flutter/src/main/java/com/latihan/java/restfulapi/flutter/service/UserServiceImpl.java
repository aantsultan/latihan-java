package com.latihan.java.restfulapi.flutter.service;

import com.latihan.java.restfulapi.flutter.aop.LoggingApi;
import com.latihan.java.restfulapi.flutter.config.jwt.JwtTokenProvider;
import com.latihan.java.restfulapi.flutter.dto.request.CreateUserRequest;
import com.latihan.java.restfulapi.flutter.dto.request.LoginRequest;
import com.latihan.java.restfulapi.flutter.dto.response.TokenResponse;
import com.latihan.java.restfulapi.flutter.dto.response.UserResponse;
import com.latihan.java.restfulapi.flutter.dto.response.WebResponse;
import com.latihan.java.restfulapi.flutter.helper.ErrorEnum;
import com.latihan.java.restfulapi.flutter.model.User;
import com.latihan.java.restfulapi.flutter.model.UserPrincipal;
import com.latihan.java.restfulapi.flutter.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;

import static com.latihan.java.restfulapi.flutter.helper.WebConstant.EXPIRE_TIME;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ValidatorService validatorService;

    @Autowired
    @Qualifier("authenticationManagerCustom")
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @LoggingApi
    @Override
    public WebResponse<String> create(CreateUserRequest request) {
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
        return WebResponse.<String>builder()
                .data("OK").build();
    }

    @LoggingApi
    @Override
    public WebResponse<TokenResponse> auth(LoginRequest request) {
        validatorService.validate(request);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getPhoneNumber(), request.getPassword()));

        User user = repository.findByPhoneNumber(request.getPhoneNumber()).get();
        user.setToken(UUID.randomUUID().toString());
        user.setTokenExpiredAt(System.currentTimeMillis() + (1000L * 60 * 60 * 24 * 30));
        repository.save(user);
        return WebResponse.<TokenResponse>builder()
                .data(TokenResponse.builder()
                        .token(user.getToken())
                        .expiredAt(user.getTokenExpiredAt())
                        .build())
                .build();
    }

    @LoggingApi
    @Override
    public WebResponse<UserResponse> get() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = null;
        if (authentication.getPrincipal() instanceof UserPrincipal) {
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            user = userPrincipal.getUser();
        }

        if (authentication.getPrincipal() instanceof String) {
            String phoneNumber = (String) authentication.getPrincipal();
            user = repository.findByPhoneNumber(phoneNumber).get();
        }

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorEnum.ERROR_401.getValue());
        }

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
    public WebResponse<TokenResponse> token() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = repository.findById(userPrincipal.getUser().getId()).get();
        user.setToken(jwtTokenProvider.generateJwtToken(userPrincipal));
        user.setTokenExpiredAt(EXPIRE_TIME);
        repository.save(user);
        return WebResponse.<TokenResponse>builder()
                .data(TokenResponse.builder()
                        .token(user.getToken())
                        .expiredAt(user.getTokenExpiredAt())
                        .build())
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
    public void extendExpiredToken(User user) {
        user.setTokenExpiredAt(EXPIRE_TIME);
        repository.save(user);
    }

    @Override
    public Optional<User> findByToken(String token) {
        return repository.findByToken(token);
    }

    @Override
    public Authentication authenticate(User user) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getPhoneNumber(), user.getPassword(), null);
        return authenticationManager.authenticate(token);
    }

    @Override
    public Authentication authenticate(User user, HttpServletRequest request) {
        return jwtTokenProvider.getAuthentication(user.getPhoneNumber(), null, request);
    }
}
