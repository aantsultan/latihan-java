package com.latihan.java.atomikos.service;

import com.latihan.java.atomikos.model.md.SalesGroup;
import com.latihan.java.atomikos.repository.md.SalesGroupRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
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
