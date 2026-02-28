package com.latihan.java.multiple.database.service;

import com.latihan.java.multiple.database.dto.hts.UserDto;
import com.latihan.java.multiple.database.helper.DtoHelper;
import com.latihan.java.multiple.database.model.hts.User;
import com.latihan.java.multiple.database.repository.hts.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(transactionManager = "htsTransactionManager")
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final DtoHelper dtoHelper;

    @Autowired
    public UserServiceImpl(UserRepository repository, DtoHelper dtoHelper) {
        this.repository = repository;
        this.dtoHelper = dtoHelper;
    }

    @Override
    public void save(User user) {
        repository.save(user);
    }

    @Override
    public List<UserDto> findAll() {
        List<User> users = repository.findAll();
        return users.stream().map(user -> dtoHelper.toDto(user, UserDto.class)).toList();
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
