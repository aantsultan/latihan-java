package com.latihan.java.multiple.database.service;

import com.latihan.java.multiple.database.dto.md.SalesGroupDto;
import com.latihan.java.multiple.database.model.md.SalesGroup;

import java.util.List;

public interface SalesGroupService {

    void save(SalesGroup salesGroup);

    void delete(SalesGroup salesGroup);

    List<SalesGroupDto> findAll();

    void deleteAll();
}
