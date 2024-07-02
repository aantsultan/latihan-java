package com.latihan.java.multiple.database.service;

import com.latihan.java.multiple.database.dto.hts.UserDto;
import com.latihan.java.multiple.database.model.hts.User;
import com.latihan.java.multiple.database.model.md.SalesGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Service
@Slf4j
public class UserSalesGroupServiceImpl implements UserSalesGroupService {

    private final UserService userService;
    private final SalesGroupService salesGroupService;
    private final PlatformTransactionManager transactionManagerMd;

    private final PlatformTransactionManager transactionManagerHts;

    private final EntityManager entityManagerMd;
    private final EntityManager entityManagerHts;


    @Autowired
    public UserSalesGroupServiceImpl(UserService userService, SalesGroupService salesGroupService
            , @Qualifier("mdTransactionManager") PlatformTransactionManager transactionManagerMd
            , @Qualifier("htsTransactionManager") PlatformTransactionManager transactionManagerHts
            , @Qualifier("mdEntityManagerFactory") EntityManager entityManagerMd
            , @Qualifier("htsEntityManagerFactory") EntityManager entityManagerHts) {
        this.userService = userService;
        this.salesGroupService = salesGroupService;
        this.transactionManagerMd = transactionManagerMd;
        this.transactionManagerHts = transactionManagerHts;
        this.entityManagerMd = entityManagerMd;
        this.entityManagerHts = entityManagerHts;
    }


    @Override
    public String save(UserDto dto) {

        DefaultTransactionDefinition definitionHts = new DefaultTransactionDefinition();
        definitionHts.setTimeout(10);
        definitionHts.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        DefaultTransactionDefinition definitionMd = new DefaultTransactionDefinition();
        definitionMd.setTimeout(10);
        definitionMd.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        TransactionStatus transactionHts = transactionManagerHts.getTransaction(definitionHts);
        TransactionStatus transactionMd = transactionManagerMd.getTransaction(definitionMd);

        try {
            SalesGroup salesGroup = new SalesGroup();
            salesGroup.setName(dto.getSalesGroup().getName());
            salesGroupService.save(salesGroup);

            long salesGroupId = salesGroup.getId();
            User user = new User();
            user.setName(dto.getName());
            user.setEmail(dto.getEmail());
            user.setSalesGroupId(salesGroupId);
            userService.save(user);

            transactionManagerHts.commit(transactionHts);
            transactionManagerMd.commit(transactionMd);
            return String.valueOf(user.getId());
        } catch (Exception e){
            transactionManagerHts.rollback(transactionHts);
            transactionManagerMd.rollback(transactionMd);
            throw new RuntimeException("ERROR");
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
            salesGroup.setName(dto.getSalesGroup().getName());
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
        } catch (Exception e){
            entityTransactionMd.rollback();
            entityTransactionHts.rollback();
            throw new RuntimeException("ERROR");
        }
    }
}
