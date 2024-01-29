package com.latihan.java.multiple.database.service;

import com.latihan.java.multiple.database.model.md.SalesGroup;
import com.latihan.java.multiple.database.repository.md.SalesGroupRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional("mdTransactionManager")
public class SalesGroupServiceImpl implements SalesGroupService {

    private final SalesGroupRepository repository;

    @Autowired
    public SalesGroupServiceImpl(SalesGroupRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(SalesGroup salesGroup) {
        repository.save(salesGroup);
    }

    @Override
    public void delete(SalesGroup salesGroup) {
        repository.delete(salesGroup);
    }
}
