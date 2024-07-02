package com.latihan.java.database.service;

import com.latihan.java.database.model.SalesGroup;
import com.latihan.java.database.repository.SalesGroupRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
public class SalesGroupServiceImpl implements SalesGroupService {

    private final SalesGroupRepository repository;

    @Autowired
    public SalesGroupServiceImpl(SalesGroupRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveAll(List<SalesGroup> salesGroup) {
        repository.saveAll(salesGroup);
    }

}
