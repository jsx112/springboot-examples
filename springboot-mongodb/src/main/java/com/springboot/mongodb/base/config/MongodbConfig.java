package com.springboot.mongodb.base.config;

import com.mongodb.MongoClient;
import com.springboot.mongodb.base.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Collection;

@Configuration
@EnableMongoRepositories(basePackages = "com.springboot.mongodb.**.dao",
        mongoTemplateRef = "mongoTemplate")
public class MongodbConfig {

    @Autowired
    private MongoProperties mongoProperties;

    @Bean(name="mongoTemplate")
    public MongoTemplate primaryMongoTemplate(MongoDbFactory mongoDbFactory) throws Exception {
        return new IdMongoTemplate(mongoDbFactory);
    }

    @Bean
    public MongoDbFactory primaryFactory() throws Exception {
        return new SimpleMongoDbFactory(new MongoClient(mongoProperties.getHost(), mongoProperties.getPort()),
                mongoProperties.getDatabase());
    }


    /**
    * 自定义MongoTemplate,负责在插入的时候自动生成id
    * @author jiasx
    * @create 2017/11/14 14:21
    **/
    public static class IdMongoTemplate  extends MongoTemplate{
        public IdMongoTemplate(MongoDbFactory mongoDbFactory) {
            super(mongoDbFactory, null);
        }

        @Override
        public void insert(Collection<?> batchToSave, Class<?> entityClass) {
            for(Object obj:batchToSave){
                //设置主键Id的值
                setIdForObj(obj);
            }
            super.insert(batchToSave, entityClass);
        }

        @Override
        public void insert(Collection<?> batchToSave, String collectionName) {
            for(Object obj:batchToSave){
                //设置主键Id的值
                setIdForObj(obj);
            }
            super.insert(batchToSave, collectionName);
        }

        @Override
        public void insert(Object objectToSave) {
            //设置主键Id的值
            setIdForObj(objectToSave);
            super.insert(objectToSave);
        }

        @Override
        public void insert(Object objectToSave, String collectionName) {
            //设置主键Id的值
            setIdForObj(objectToSave);
            super.insert(objectToSave, collectionName);
        }

        @Override
        public void insertAll(Collection<?> objectsToSave) {
            for(Object obj:objectsToSave){
                //设置主键Id的值
                setIdForObj(obj);
            }
            super.insertAll(objectsToSave);
        }

        @Override
        public void save(Object objectToSave) {
            //设置主键Id的值
            setIdForObj(objectToSave);
            super.save(objectToSave);
        }

        @Override
        public void save(Object objectToSave, String collectionName) {
            //设置主键Id的值
            setIdForObj(objectToSave);
            super.save(objectToSave, collectionName);
        }

        /**
         * 设置主键id的值，根据注解@Id去判断主键
         * @param obj
         * @return
         */
        public void setIdForObj(Object obj) {
            if(obj==null ){
                return ;
            }

            // 解析字段上是否有注解
            // ps：getDeclaredFields会返回类所有声明的字段，包括private、protected、public，但是不包括父类的
            Field idField = null;
            Field[] fields = obj.getClass().getDeclaredFields();
            for(Field field : fields){
                boolean fieldHasAnno = field.isAnnotationPresent(Id.class);
                //如果发现此注解，需要注入值
                if(fieldHasAnno){
                    idField = field;
                    break;
                }
            }

            //设置主键的值,当用户没有设置自己的id时
            if(idField!=null){
                ReflectionUtils.makeAccessible(idField);
                if(ReflectionUtils.getField(idField,obj)==null){
                    ReflectionUtils.setField(idField, obj, IdUtil.generateId());
                }
            }

        }
    }

}