package com.springboot.mutidatasource.admin.test.service.impl;/**
 * Created by dell on 2017/11/22.
 */

import com.springboot.mutidatasource.admin.test.entity.Test;
import com.springboot.mutidatasource.admin.test.repository.mysql.TestRepository;
import com.springboot.mutidatasource.admin.test.service.TestService;
import com.springboot.mutidatasource.base.repository.mysql.BaseRepository;
import com.springboot.mutidatasource.base.service.mysql.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户管理服务实现
 *
 * @author jiasx
 * @create 2017-11-22 18:12
 **/
@Service
public class TestServiceImpl extends BaseServiceImpl<Test,String> implements TestService {

    @Autowired
    private TestRepository userRepository;


    @Override
    public BaseRepository<Test, String> getBaseRepository() {
        return this.userRepository;
    }

    @Override
    public Test insert(Test user){
        return userRepository.save(user);
    }

    @Override
    public Test findOneByAccount(String account){
        return userRepository.findOneByAccount(account);
    }

}
