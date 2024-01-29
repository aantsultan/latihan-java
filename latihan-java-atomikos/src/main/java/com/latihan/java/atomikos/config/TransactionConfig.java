package com.latihan.java.atomikos.config;

import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.SystemException;

@Configuration
@EnableTransactionManagement
public class TransactionConfig {

    @Bean(initMethod = "init", destroyMethod = "close", name = "userTransactionManager")
    public UserTransactionManager userTransactionManager() throws SystemException {
        UserTransactionManager manager = new UserTransactionManager();
        manager.setTransactionTimeout(300);
        manager.setForceShutdown(true);
        return manager;
    }

    @Bean(name = "transactionManager")
    public JtaTransactionManager transactionManager(@Qualifier("userTransactionManager") UserTransactionManager userTransactionManager){
        JtaTransactionManager manager = new JtaTransactionManager();
        manager.setTransactionManager(userTransactionManager);
        manager.setUserTransaction(userTransactionManager);
        return manager;
    }

}
