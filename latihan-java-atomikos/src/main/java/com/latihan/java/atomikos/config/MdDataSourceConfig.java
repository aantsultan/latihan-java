package com.latihan.java.atomikos.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.util.HashMap;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "mdEntityManagerFactory"
        , transactionManagerRef = "transactionManager" // atomikos api
        , basePackages = "com.latihan.java.atomikos.repository.md"
)
public class MdDataSourceConfig {

    private final Environment environment;

    @Autowired
    public MdDataSourceConfig(Environment environment) {
        this.environment = environment;
    }


    // atomikos api
    @Bean(initMethod = "init", destroyMethod = "close", name = "mdDataSource")
    public AtomikosDataSourceBean mdDataSource() {
        AtomikosDataSourceBean dataSource = new AtomikosDataSourceBean();
        dataSource.setLocalTransactionMode(true);
        dataSource.setUniqueResourceName("md");
        dataSource.setXaDataSourceClassName("com.microsoft.sqlserver.jdbc.SQLServerXADataSource");
        Properties properties = new Properties();
        properties.put("databaseName", "latihan");
        properties.put("serverName", "localhost");
        properties.put("portNumber", "1433");
        properties.put("encrypt", true);
        properties.put("trustServerCertificate", true);
        properties.put("user", environment.getProperty("spring.datasource-md.username"));
        properties.put("password", environment.getProperty("spring.datasource-md.password"));
        dataSource.setXaProperties(properties);
        dataSource.setPoolSize(10);
        return dataSource;
    }

    @Primary
    @Bean(name = "mdEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean mdEntityManagerFactory(@Qualifier("mdDataSource") AtomikosDataSourceBean dataSource
    ) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.latihan.java.atomikos.model.md");

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto-md"));
        properties.put("hibernate.dialect", environment.getProperty("hibernate.dialect-md"));
        properties.put("hibernate.cache.use_second_level_cache", "false");
        properties.put("hibernate.cache.use_query_cache", "false");

        // atomikos api
        properties.put("hibernate.current_session_context_class", "jta");
        properties.put("javax.persistence.transactionType", "jta");
        properties.put("hibernate.transaction.manager_lookup_class", "com.atomikos.icatch.jta.hibernate3.TransactionManagerLookup");

        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(adapter);
        em.setJpaPropertyMap(properties);
        return em;
    }

}
