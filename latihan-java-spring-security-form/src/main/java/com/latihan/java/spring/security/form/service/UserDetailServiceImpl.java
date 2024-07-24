package com.latihan.java.spring.security.form.service;

import com.latihan.java.spring.security.form.model.User;
import com.latihan.java.spring.security.form.model.UserPrincipal;
import com.latihan.java.spring.security.form.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        if(user == null){
            throw new RuntimeException("User or password is incorrect");
        }
        return new UserPrincipal(user);
    }
}
