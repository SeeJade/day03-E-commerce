package com.pinyougou.service;

import com.pinyougou.vo.PageResult;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T> {
    //根据主键查询
    //T findOne(Object id);
    //因为主键类型都需要实现Serializable
    T findOne(Serializable id);

    //查询全部
    List<T> findAll();

    //根据条件查询
    List<T> findByWhere(T t);

    //根据分页查询
    PageResult findPage(Integer page, Integer rows);

    //根据分页条件查询
    PageResult findPage(Integer page, Integer rows, T t);

    //新增
    void add(T t);

    //根据主键更新
    void update(T t);

    //批量删除
    void deleteByIds(Serializable[] ids);
}
