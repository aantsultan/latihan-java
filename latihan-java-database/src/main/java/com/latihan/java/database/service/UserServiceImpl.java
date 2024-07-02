package com.latihan.java.database.service;

import com.latihan.java.database.dto.SalesGroupDto;
import com.latihan.java.database.dto.UserDto;
import com.latihan.java.database.model.User;
import com.latihan.java.database.repository.UserRepository;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(User user) {
        repository.save(user);
    }

    @Override
    public List<UserDto> findAll(Integer page) {
        Pageable pageable = PageRequest.of(page - 1, 10);
        Slice<User> result = repository.findAll(pageable);
        return result.getContent().stream().map(data -> {
            List<SalesGroupDto> salesGroupDtos = data.getSalesGroups().stream().map(salesGroup -> SalesGroupDto.builder()
                    .id(salesGroup.getId())
                    .name(salesGroup.getName())
                    .build()).collect(Collectors.toList());
            return UserDto.builder()
                    .id(data.getId())
                    .email(data.getEmail())
                    .name(data.getName())
                    .salesGroup(salesGroupDtos)
                    .build();
        }).collect(Collectors.toList());
    }
}
