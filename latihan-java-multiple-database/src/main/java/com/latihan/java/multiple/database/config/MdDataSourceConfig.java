package com.latihan.java.multiple.database.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "mdEntityManagerFactory"
        , transactionManagerRef = "mdTransactionManager"
        , basePackages = "com.latihan.java.multiple.database.repository.md"
)
public class MdDataSourceConfig {

    private final Environment environment;

    @Autowired
    public MdDataSourceConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean(name = "mdDataSource")
    @ConfigurationProperties(prefix = "spring.datasource-md")
    public DataSource mdDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mdEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean mdEntityManagerFactory(@Qualifier("mdDataSource") DataSource dataSource
    ) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.latihan.java.multiple.database.model.md");

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto-md"));
        properties.put("hibernate.dialect", environment.getProperty("hibernate.dialect-md"));
        properties.put("hibernate.cache.use_second_level_cache", "false");
        properties.put("hibernate.cache.use_query_cache", "false");

        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(adapter);
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean(name = "mdTransactionManager")
    public PlatformTransactionManager mdTransactionManager(@Qualifier("mdEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
