package com.latihan.java.spring.mongodb.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.latihan.java.spring.mongodb.repository")
public class MongoConfig {

    @Value("${mongodb.connection.url}")
    private String connectionUrl;

    @Value("${mongodb.connection.name}")
    private String connectionName;

    @Bean(name = "mongoClient")
    public MongoClient mongoClient(){
        ConnectionString connectionString = new ConnectionString(connectionUrl);
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        return MongoClients.create(settings);
    }

    @Bean
    public MongoTemplate mongoTemplate(@Qualifier("mongoClient") MongoClient mongoClient){
        return new MongoTemplate(mongoClient, connectionName);
    }

}
