package com.latihan.java.restfulapi.flutter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static com.latihan.java.restfulapi.flutter.helper.WebConstant.WHITELIST_URL;

@Configuration
public class SecurityConfig {

    @Autowired
    private FilterCustom filterCustom;

    @Autowired
    private EntryPointCustom entryPointCustom;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           @Qualifier("authenticationManagerCustom") AuthenticationManager authenticationManager)
            throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests(auth -> {
                    auth.mvcMatchers(WHITELIST_URL).permitAll();
                    auth.anyRequest().authenticated();
                })
                .httpBasic()
                .and()
                .exceptionHandling().authenticationEntryPoint(entryPointCustom)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationManager(authenticationManager)
                .addFilterBefore(filterCustom, BasicAuthenticationFilter.class);
        return http.build();
    }
}
