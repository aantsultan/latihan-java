package com.latihan.java.spring.restfulapi.resolver;

import com.latihan.java.spring.restfulapi.entity.User;
import com.latihan.java.spring.restfulapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;

@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    private final UserRepository userRepository;

    @Autowired
    public UserArgumentResolver(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return User.class.equals(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest servletRequest = (HttpServletRequest) webRequest.getNativeRequest();
        String token = servletRequest.getHeader("X-API-TOKEN");
        if(token == null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }

        User user = userRepository.findFirstByToken(token).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized"));

        if(user.getTokenExpiredAt() < System.currentTimeMillis()) {
            throw  new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }

        return user;
    }
}
