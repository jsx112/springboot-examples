package com.springboot.quartz.base;

import java.io.Serializable;

import com.springboot.quartz.util.Utils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

/**
* 自定义主键生成策略
* @author jiasx
* @create 2017/9/23 10:20
**/
public class GlobalIdGenerator implements IdentifierGenerator {

    /**
     * 生成自定义主键，在jpa中使用
     *
     */
    public Serializable generate(SessionImplementor session, Object obj)  throws HibernateException {
        Long id = null;
        if(obj instanceof BaseEntry){
            if(((BaseEntry)obj).getId()!=null){
                id = ((BaseEntry)obj).getId();
            }
        }
        return id==null? Utils.generateId():id;
    }
 
}