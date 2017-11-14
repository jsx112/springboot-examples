package com.springboot.mongodb.base.util;


/**
 * 全局唯一id生成工具类
 *
 * @author ysh
 **/
public class IdUtil {

    /**
    * id生成工具类
    **/
    public static IdWorker idWorker;


    /**
    * 获取全局唯一id，支持分布式
    **/
    public static Long generateId() {
        if (idWorker == null) {
            throw new RuntimeException("idWorker实例为空，需要初始化设置");
        }
        return idWorker.nextId();
    }
}