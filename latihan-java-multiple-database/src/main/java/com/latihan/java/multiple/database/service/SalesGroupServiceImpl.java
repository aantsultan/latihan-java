package com.latihan.java.multiple.database.service;

import com.latihan.java.multiple.database.dto.md.SalesGroupDto;
import com.latihan.java.multiple.database.helper.DtoHelper;
import com.latihan.java.multiple.database.model.md.SalesGroup;
import com.latihan.java.multiple.database.repository.md.SalesGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(transactionManager = "mdTransactionManager")
public class SalesGroupServiceImpl implements SalesGroupService {

    private final SalesGroupRepository repository;
    private final DtoHelper dtoHelper;

    @Autowired
    public SalesGroupServiceImpl(SalesGroupRepository repository, DtoHelper dtoHelper) {
        this.repository = repository;
        this.dtoHelper = dtoHelper;
    }

    @Override
    public void save(SalesGroup salesGroup) {
        repository.save(salesGroup);
    }

    @Override
    public void delete(SalesGroup salesGroup) {
        repository.delete(salesGroup);
    }

    @Override
    public List<SalesGroupDto> findAll() {
        return repository.findAll().stream().map(data -> dtoHelper.toDto(data, SalesGroupDto.class)).toList();
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
