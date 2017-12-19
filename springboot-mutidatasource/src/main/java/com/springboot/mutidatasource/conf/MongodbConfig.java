package com.springboot.mutidatasource.conf;

import com.mongodb.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperationContext;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.util.Assert;

import static org.springframework.data.mongodb.core.query.SerializationUtils.serializeToJsonSafely;

@Configuration
@EnableMongoRepositories(basePackages = "com.springboot.mutidatasource.admin.**.repository.mongo",
        mongoTemplateRef = "mongoTemplate")
public class MongodbConfig {

    @Autowired
    private MongoProperties mongoProperties;

    @Bean(name="mongoTemplate")
    public MongoTemplate primaryMongoTemplate(MongoDbFactory mongoDbFactory) throws Exception {
        return new MongoTemplate(mongoDbFactory);
    }

    @Bean
    public MongoDbFactory primaryFactory() throws Exception {
        return new SimpleMongoDbFactory(new MongoClientURI(mongoProperties.getUri()));
    }


}