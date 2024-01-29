package com.latihan.java.atomikos.service;

import com.latihan.java.atomikos.dto.hts.UserDto;
import com.latihan.java.atomikos.model.hts.User;
import com.latihan.java.atomikos.model.md.SalesGroup;
import com.latihan.java.atomikos.repository.hts.UserRepository;
import com.latihan.java.atomikos.repository.md.SalesGroupRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserSalesGroupServiceImpl implements UserSalesGroupService {

    private final UserRepository userRepository;
    private final SalesGroupRepository salesGroupRepository;

    @Autowired
    public UserSalesGroupServiceImpl(UserRepository userRepository, SalesGroupRepository salesGroupRepository) {
        this.userRepository = userRepository;
        this.salesGroupRepository = salesGroupRepository;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public String save(UserDto dto) {
        SalesGroup salesGroup = new SalesGroup();
        salesGroup.setName(dto.getSalesGroup().getName());
        salesGroupRepository.save(salesGroup);
        long salesGroupId = salesGroup.getId();
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setSalesGroupId(salesGroupId);
        userRepository.save(user);
        return String.valueOf(user.getId());
    }
}
