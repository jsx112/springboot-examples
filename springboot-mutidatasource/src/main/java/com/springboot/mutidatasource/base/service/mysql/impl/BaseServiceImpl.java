package com.springboot.mutidatasource.base.service.mysql.impl;/**
 * Created by dell on 2017/11/24.
 */

import com.springboot.mutidatasource.base.entity.BaseEntity;
import com.springboot.mutidatasource.base.repository.mysql.BaseRepository;
import com.springboot.mutidatasource.base.service.mysql.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * 基础服务实现类
 *
 * @author jiasx
 * @create 2017-11-24 14:46
 **/
@Transactional
public abstract class BaseServiceImpl<T extends BaseEntity, ID extends Serializable> implements BaseService<T, ID> {

    public abstract BaseRepository<T, ID> getBaseRepository();

    @Override
    public T find(ID id) {
        return getBaseRepository().findOne(id);
    }

    @Override
    public List<T> findAll() {
        return getBaseRepository().findAll();
    }

    @Override
    public Iterable<T> findList(ID[] ids) {
        List<ID> idList = Arrays.asList(ids);
        return getBaseRepository().findAll(idList);
    }

    @Override
    public Iterable<T> findList(Iterable<ID> ids) {
        return getBaseRepository().findAll(ids);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return getBaseRepository().findAll(pageable);
    }

    @Override
    public long count() {
        return getBaseRepository().count();
    }

    @Override
    public boolean exists(ID id) {
        return getBaseRepository().exists(id);
    }

    @Override
    public T insert(T entity) {
        return getBaseRepository().save(entity);
    }

    @Override
    public List<T> insert(Iterable<T> entitys) {
        return getBaseRepository().save(entitys);
    }

    @Override
    public T update(T entity) {
        return getBaseRepository().save(entity);
    }

    @Override
    public List<T> update(Iterable<T> entitys){
        return getBaseRepository().save(entitys);
    }

    @Override
    public void delete(ID id) {
        getBaseRepository().delete(id);
    }

    @Override
    public void deleteByIds(@SuppressWarnings("unchecked") ID... ids) {
        if (ids != null) {
            for (int i = 0; i < ids.length; i++) {
                ID id = ids[i];
                this.delete(id);
            }
        }
    }

    @Override
    public void delete(T[] entitys) {
        List<T> tList = Arrays.asList(entitys);
        getBaseRepository().delete(tList);
    }

    @Override
    public void delete(Iterable<T> entitys) {
        getBaseRepository().delete(entitys);
    }

    @Override
    public void delete(T entity) {
        getBaseRepository().delete(entity);
    }



}
