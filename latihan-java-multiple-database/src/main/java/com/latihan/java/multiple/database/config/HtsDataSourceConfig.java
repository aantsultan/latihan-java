package com.latihan.java.multiple.database.config;

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

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "htsEntityManagerFactory"
        , transactionManagerRef = "htsTransactionManager"
        , basePackages = "com.latihan.java.multiple.database.repository.hts"
)
public class HtsDataSourceConfig {

    private final Environment environment;

    @Autowired
    public HtsDataSourceConfig(Environment environment) {
        this.environment = environment;
    }

    @Primary
    @Bean(name = "htsDataSource")
    @ConfigurationProperties(prefix = "spring.datasource-hts")
    public DataSource htsDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "htsEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean htsEntityManagerFactory(@Qualifier("htsDataSource") DataSource dataSource
    ) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.latihan.java.multiple.database.model.hts");

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto-hts"));
        properties.put("hibernate.dialect", environment.getProperty("hibernate.dialect-hts"));
        properties.put("hibernate.cache.use_second_level_cache", "false");
        properties.put("hibernate.cache.use_query_cache", "false");

        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(adapter);
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Primary
    @Bean(name = "htsTransactionManager")
    public PlatformTransactionManager htsTransactionManager(@Qualifier("htsEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
