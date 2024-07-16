package com.latihan.java.restfulapi.flutter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import java.util.HashMap;
import java.util.Map;

import static com.latihan.java.restfulapi.flutter.helper.WebConstant.WHITELIST_URL;

@Configuration
public class SecurityConfig {

    @Autowired
    private FilterCustom filterCustom;

    @Autowired
    private DeniedHandlerCustom deniedHandlerCustom;

    @Autowired
    private EntryPointCustom entryPointCustom;

    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests(auth -> {
                    auth.mvcMatchers(WHITELIST_URL).permitAll();
                    auth.anyRequest().authenticated();
                })
                .addFilterBefore(filterCustom, FilterSecurityInterceptor.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManagerCustom(PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(authenticationProvider);
    }
}
