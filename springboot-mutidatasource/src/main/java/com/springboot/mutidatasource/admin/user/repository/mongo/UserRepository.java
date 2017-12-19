package com.springboot.mutidatasource.admin.user.repository.mongo;

import com.springboot.mutidatasource.admin.user.entity.User;
import com.springboot.mutidatasource.base.repository.mongo.BaseRepository;

/**
* 继承自MongoRepository接口，MongoRepository接口包含了常用的CRUD操作，
* 例如：save、insert、findall等等。我们可以定义自己的查找接口(遵循jpa规范)，
* @author jiasx
* @create 2017/11/13 15:48
**/
public interface UserRepository extends BaseRepository<User, String> {

    User  findOneByAccount(String account);

}