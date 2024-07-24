package com.latihan.java.spring.security.form.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@Order(1)
public class WebSecurityConfig {

    private final UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain configureWeb(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .antMatcher("/**")
                .authorizeHttpRequests(auth -> {
                    auth.antMatchers("/register").permitAll();
                    auth.anyRequest().authenticated();
                })
                .exceptionHandling(handling -> handling
                        .accessDeniedPage("/403"))
                .formLogin(login -> login
                        .loginPage("/login")
                        .loginProcessingUrl("/security_check")
                        .defaultSuccessUrl("/")
                        .failureUrl("/login?error=true")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .permitAll())
                .logout(logout -> logout.logoutUrl("/logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl("/logout-successful")
                        .permitAll())
                .rememberMe().rememberMeParameter("remember-me")
                .key("myKey").userDetailsService(userDetailsService)
                .tokenValiditySeconds(3 * 24 * 60 * 60);
        return http.build();
    }
}
