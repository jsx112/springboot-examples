package com.jsx.base;

import java.io.Serializable;
import java.util.Properties;

import com.jsx.util.Utils;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.PersistentIdentifierGenerator;
import org.hibernate.id.SequenceGenerator;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;
 
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
        return id==null?Utils.generateId():id;
    }
 
}