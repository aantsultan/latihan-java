package com.latihan.java.spring.security.form.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

@Configuration
@RequiredArgsConstructor
@Order(2)
public class MobileSecurityConfig {

    private final UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain configureMobile(HttpSecurity http) throws Exception {
        RegexRequestMatcher matcher = new RegexRequestMatcher("^/mobile/login\\?username=*\\w*&password=*\\w*$", "GET");
        http.csrf().disable()
                .antMatcher("/mobile/**")
                .authorizeHttpRequests(auth -> auth.requestMatchers(matcher).permitAll())
                .exceptionHandling(handling -> handling
                        .accessDeniedPage("/mobile/403"))
                .formLogin(login -> login
                        .loginPage("/mobile/login")
                        .loginProcessingUrl("/mobile/security_check")
                        .defaultSuccessUrl("/mobile")
                        .failureUrl("/mobile/login?error=true")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .permitAll())
                .logout(logout -> logout.logoutUrl("/mobile/logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl("/mobile/logout-successful")
                        .permitAll())
                .rememberMe().rememberMeParameter("remember-me")
                .key("myKey").userDetailsService(userDetailsService)
                .tokenValiditySeconds(3 * 24 * 60 * 60);
        return http.build();
    }

}
