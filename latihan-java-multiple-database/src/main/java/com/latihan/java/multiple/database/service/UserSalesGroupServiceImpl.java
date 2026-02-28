package com.latihan.java.multiple.database.service;

import com.latihan.java.multiple.database.dto.UserSalesGroupDto;
import com.latihan.java.multiple.database.dto.hts.UserDto;
import com.latihan.java.multiple.database.exception.TransactionException;
import com.latihan.java.multiple.database.model.hts.User;
import com.latihan.java.multiple.database.model.md.SalesGroup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserSalesGroupServiceImpl implements UserSalesGroupService {

    private final UserService userService;
    private final SalesGroupService salesGroupService;

    private final EntityManager entityManagerMd;
    private final EntityManager entityManagerHts;

    public UserSalesGroupServiceImpl(UserService userService, SalesGroupService salesGroupService
            , @Qualifier("mdEntityManagerFactory") EntityManager entityManagerMd
            , @Qualifier("htsEntityManagerFactory") EntityManager entityManagerHts) {
        this.userService = userService;
        this.salesGroupService = salesGroupService;
        this.entityManagerMd = entityManagerMd;
        this.entityManagerHts = entityManagerHts;
    }

    @Override
    @Transactional(transactionManager = "chainTransactionManager")
    public String save(UserSalesGroupDto dto) {
        try {
            SalesGroup salesGroup = new SalesGroup();
            salesGroup.setName(dto.getSalesGroup().getName());
            salesGroupService.save(salesGroup);

            User user = new User();
            user.setName(dto.getName());
            user.setEmail(dto.getEmail());
            user.setSalesGroupId(salesGroup.getId());
            userService.save(user);
            return "Success save user salesgroup";
        } catch (Exception e) {
            throw new TransactionException(String.format("Failed to save user salesgroup. Reason : %s", e.getMessage()));
        }
    }

    @Override
    public String saveEm(UserDto dto) {

        EntityTransaction entityTransactionMd = entityManagerMd.getTransaction();
        EntityTransaction entityTransactionHts = entityManagerHts.getTransaction();

        entityTransactionMd.begin();
        entityTransactionHts.begin();
        try {
            SalesGroup salesGroup = new SalesGroup();
//            salesGroup.setName(dto.getSalesGroup().getName());
            entityManagerMd.persist(salesGroup);

            long salesGroupId = salesGroup.getId();
            User user = new User();
            user.setName(dto.getName());
            user.setEmail(dto.getEmail());
            user.setSalesGroupId(salesGroupId);
            entityManagerHts.persist(user);

            entityTransactionMd.commit();
            entityTransactionHts.commit();
            return String.valueOf(user.getId());
        } catch (Exception e) {
            entityTransactionMd.rollback();
            entityTransactionHts.rollback();
            throw new RuntimeException("ERROR");
        }
    }
}
