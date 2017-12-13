package com.springboot.sitemesh.base.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.List;

/**
* 基础服务接口类
* @author jiasx
* @create 2017/11/24 14:46
**/
public interface BaseService<T, ID extends Serializable> {
    T find(ID id);

    List<T> findAll();

    Iterable<T> findList(ID[] ids);

    Iterable<T> findList(Iterable<ID> ids);

    Page<T> findAll(Pageable pageable);

    long count();

    boolean exists(ID id);

    T insert(T entity);

    List<T> insert(Iterable<T> entitys);

    T update(T entity);

    List<T> update(Iterable<T> entitys);

    void delete(ID id);

    void deleteByIds(@SuppressWarnings("unchecked") ID... ids);

    void delete(T[] entitys);

    void delete(Iterable<T> entitys);

    void delete(T entity);

}
