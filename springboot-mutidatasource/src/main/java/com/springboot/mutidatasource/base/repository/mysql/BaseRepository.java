package com.springboot.mutidatasource.base.repository.mysql;

import com.springboot.mutidatasource.base.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
* mysql jpa基础类
* @author jiasx
* @create 2017/12/18 15:54
**/
public interface BaseRepository<T extends BaseEntity, ID extends Serializable> extends JpaRepository<T,ID> {
    
}