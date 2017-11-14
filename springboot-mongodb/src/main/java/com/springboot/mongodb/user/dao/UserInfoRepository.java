package com.springboot.mongodb.user.dao;

import com.springboot.mongodb.user.entity.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
* 继承自MongoRepository接口，MongoRepository接口包含了常用的CRUD操作，
* 例如：save、insert、findall等等。我们可以定义自己的查找接口(遵循jpa规范)，
* 例如根据UserInfo的account搜索，UserInfoRepository：
* @author jiasx
* @create 2017/11/13 15:48
**/
public interface UserInfoRepository extends MongoRepository<UserInfo, Long> {
    List<UserInfo> findByAccount(String account);
}