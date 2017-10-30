package com.springboot.redis.controller;/**
 * Created by dell on 2017/10/30.
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Redis测试服务类,注解缓存
 *
 * @author jiasx
 * @create 2017-10-30 17:51
 **/
@RestController
@RequestMapping("/redis_anon")
@Slf4j
public class RedisTestAnnonationController {


    /**
     *  对于使用@Cacheable标注的方法，Spring在每次执行前都会检查Cache中是否存在相同key的缓存元素，如果存在就不再执行该方法，而是直接从缓存中获取结
     *  果进行返回，否则才会执行并将返回结果存入指定的缓存中。
     * @return
     */
    @RequestMapping("/get")
    @Cacheable(value="users", key="#userId")
    public String get(String userId,String operate){
        if(operate==null){//可以从数据库中查询
            operate = "redis没有存储";
        }
        return operate;
    }

    /**
     *  @CachePut也可以声明一个方法支持缓存功能。与@Cacheable不同的是使用@CachePut标注的方法在执行前不会去检查缓存中是否存
     *  在之前执行过的结果，而是每次都会执行该方法，并将执行结果以键值对的形式存入指定的缓存中
     * @return
     */
    @RequestMapping("/set")
    @CachePut(value="users", key="#userId")
    public String set(String userId,String operate){
        if(operate==null){//可以从数据库中查询
            operate = "redis没有存储";
        }
        return operate;
    }

    /**
     *  @CacheEvict是用来标注在需要清除缓存元素的方法或类上的。当标记在一个类上时表示其中所有的方法的执行都会触发缓存的清除操作。
     *  @CacheEvict可以指定的属性有value、key、condition、allEntries和beforeInvocation。
     *  其中value、key和condition的语义与@Cacheable对应的属性类似。即value表示清除操作是发生在哪些Cache上的（对应Cache的名称）；
     *  key表示需要清除的是哪个key，如未指定则会使用默认策略生成的key；condition表示清除操作发生的条件
     * @return
     */
    @RequestMapping("/del")
    @CacheEvict(value="users", key="#userId")
    public String del(String userId){
        return "del_success";
    }


}
