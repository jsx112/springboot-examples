package com.springboot.mutidatasource.base.service.mysql;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

/**
* 基础服务接口类
* @author jiasx
* @create 2017/11/24 14:46
**/
public interface BaseService<T, ID extends Serializable> {

    /**
     * 根据主键id查询当前对象
     * @param id
     * @return T
     */
    T find(ID id);

    /**
     * 查询对象集合
     * @return List<T>
     */
    List<T> findAll();

    /**
     * 根据id集合查询对象
     * @param ids
     * @return Iterable<T>
     */
    Iterable<T> findList(ID[] ids);

    /**
     * 根据id集合查询对象
     * @param ids
     * @return Iterable<T>
     */
    Iterable<T> findList(Iterable<ID> ids);

    /**
     * 获取分页对象列表
     * @param pageable
     * @return Page<T>
     */
    Page<T> findAll(Pageable pageable);

    /**
     * 查询所有的记录数
     * @return long
     */
    long count();

    /**
     * 根据主键id查询是否存在记录
     * @param id
     * @return Boolean
     */
    boolean exists(ID id);

    /**
     * 新增对象
     * @param entity
     * @return T
     */
    T insert(T entity);

    /**
     * 新增多个对象
     * @param entitys
     * @return List<T>
     */
    List<T> insert(Iterable<T> entitys);

    /**
     * 更新对象
     * @param entity
     * @return T
     */
    T update(T entity);

    /**
     * 更新多个对象
     * @param entitys
     * @return List<T>
     */
    List<T> update(Iterable<T> entitys);

    /**
     * 根据主键id删除当前对象
     * @param id
     */
    void delete(ID id);

    /**
     * 删除多个对象
     * @param ids
     */
    void deleteByIds(@SuppressWarnings("unchecked") ID... ids);

    /**
     * 删除多个对象
     * @param entitys
     */
    void delete(T[] entitys);

    /**
     * 删除多个对象
     * @param entitys
     */
    void delete(Iterable<T> entitys);

    /**
     * 删除当前对象
     * @param entity
     */
    void delete(T entity);

}
