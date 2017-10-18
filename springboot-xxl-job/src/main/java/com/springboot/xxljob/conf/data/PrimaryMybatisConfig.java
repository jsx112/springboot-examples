package com.springboot.xxljob.conf.data;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;


import javax.sql.DataSource;

/**
* mybatis数据库配置
* @author jiasx
* @create 2017/9/15 15:33
**/
@Configuration
@MapperScan(basePackages = "com.jsx.**.dao.mybatis", sqlSessionTemplateRef  = "primarySqlSessionTemplate")
public class PrimaryMybatisConfig {

    private static String MAPPER_PATH = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + "com/springboot/xxljob/**/dao/**/*.xml";

    @Bean(name = "primarySqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("primaryDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_PATH));
        bean.setTypeAliasesPackage("com.jsx.**.entity");

        return bean.getObject();
    }

    @Bean(name = "transactionManagerPrimary")
    @Primary
    public DataSourceTransactionManager transactionManager(@Qualifier("primaryDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "primarySqlSessionTemplate")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("primarySqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}