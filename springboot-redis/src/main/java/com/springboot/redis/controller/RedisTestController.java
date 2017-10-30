package com.springboot.redis.controller;/**
 * Created by dell on 2017/10/30.
 */

import com.springboot.redis.dao.RedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Redis测试服务类
 *
 * @author jiasx
 * @create 2017-10-30 17:51
 **/
@RestController
@RequestMapping("/redis")
@Slf4j
public class RedisTestController {


    @Autowired
    private RedisRepository<String,String> redisRepository;

    @RequestMapping("/add")
    public void add(String userId,String operate){
        redisRepository.set(userId,operate);
    }

    @RequestMapping("/get")
    public String get(String userId){
        String operate = null;
        if(userId!=null){
            operate =redisRepository.get(userId);
        }
        if(operate==null){//可以从数据库中查询
            operate = "redis没有存储";
        }
        return operate;
    }


}
