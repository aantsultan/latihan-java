package com.latihan.java.database.service;

import com.latihan.java.database.dto.UserDto;
import com.latihan.java.database.model.SalesGroup;
import com.latihan.java.database.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class UserSalesGroupServiceImpl implements UserSalesGroupService {

    private final UserService userService;
    private final SalesGroupService salesGroupService;


    @Autowired
    public UserSalesGroupServiceImpl(UserService userService, SalesGroupService salesGroupService) {
        this.userService = userService;
        this.salesGroupService = salesGroupService;
    }

    @Override
    public String save(UserDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        userService.save(user);
        List<SalesGroup> salesGroups = dto.getSalesGroup().stream()
                .map(data -> new SalesGroup(data.getName(), user))
                .collect(Collectors.toList());
        salesGroupService.saveAll(salesGroups);
        return String.valueOf(user.getId());
    }
}
