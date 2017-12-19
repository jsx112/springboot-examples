package com.springboot.mutidatasource.admin.user.service.impl;/**
 * Created by dell on 2017/11/22.
 */

import com.springboot.mutidatasource.admin.user.entity.User;
import com.springboot.mutidatasource.admin.user.repository.mongo.UserRepository;
import com.springboot.mutidatasource.admin.user.service.UserService;
import com.springboot.mutidatasource.base.repository.mongo.BaseRepository;
import com.springboot.mutidatasource.base.service.mongo.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户管理服务实现
 *
 * @author jiasx
 * @create 2017-11-22 18:12
 **/
@Service
public class UserServiceImpl extends BaseServiceImpl<User,String> implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public BaseRepository<User, String> getBaseRepository() {
        return this.userRepository;
    }

    @Override
    public User insert(User user){
        return userRepository.insert(user);
    }

    @Override
    public User  findOneByAccount(String account){
        return userRepository.findOneByAccount(account);
    }


}
