package com.latihan.java.multiple.database.repository;

import com.latihan.java.multiple.database.model.md.SalesGroup;
import com.latihan.java.multiple.database.repository.md.SalesGroupRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SalesGroupRepositoryTest {

    @Autowired
    private SalesGroupRepository repository;

    @Test
    void findAll() {
        List<SalesGroup> salesGroups = repository.findAll();
        Assertions.assertNotNull(salesGroups);
    }

}
