package com.hebta.common.persistence.service;

import java.io.Serializable;
import java.util.List;

import com.hebta.common.persistence.PageResult;

import tk.mybatis.mapper.entity.Example;

/**
 * *业务处理对象基础类
 * @author qw
 *
 */
public interface BaseService<T> {

	  /**
     ** 插入数据
     * @param entity
     * @return
     */
    int insert(T entity);
    
    /**
     ** 插入数据(忽略null)
     * @param entity
     * @return
     */
    int insertSelective(T entity);

    /**
     * *批量新增
     * @param list
     * @return
     */
    int insertBatch(List<T> list);

    /**
     * *更新数据
     * @param entity
     * @return
     */
    int update(T entity);
    
    /**
     * 更新数据(忽略null)
     * @return
     */
    int updateSelective(T entity);
    
    /**
     * *根据条件批量更新数据
     * @param entity
     * @return
     */
    int updateByExample(T entity, Example example);

    /**
     * *主键删除
     * @param id
     * @return
     */
    int deleteById(Serializable id);

    /**
     * *根据字段名称和字段值获取唯一记录
     * @param column	PS：column为数据库字段名如 del_flag
     * @param value
     * @return
     */
    T selectByProperty(String column,Object value);


    /**
     * *根据字段名称和字段值获取记录列表
     * @param column   PS：column为数据库字段名如 del_flag
     * @param value
     * @return
     */
    List<T> listByProperty(String column, Object value);


    /**
     * *根据主键获取单条数据
     * @param id
     * @return
     */
    T selectById(Serializable id);
    
    
    /**
     * 根据id和groupId获取单条数据
     * 
     * @param id
     * @param groupId
     * @param entityClass
     * @return
     */
    T selectByIdAndGroupId(Serializable id, String groupId, Class<?> entityClass);
    
    /**
     * *查询数据列表
     * @param entity
     * @return
     */
    List<T> select(T entity);

    /**
     * *查询数据列表(分页)
     * @param entity
     * @return
     */
    PageResult<T> selectPage(T entity, int page, int pageSize);

    /**
     * *查询所有数据列表
     * @return
     */
    List<T> selectAllList();

    /**
     * *获取数量
     *
     * @param entity
     * @return
     */
    int count(T entity);
    
    /**
     * *条件查询列表
     * @param example
     * @return
     */
    List<T> selectByExample(Example example);
    
    /**
     * *条件查询列表(分页)
     * @param example
     * @param page
     * @param pageSize
     * @return
     */
    PageResult<T> selectByExample(Example example, int page, int pageSize);
    
    /**
     * *条件查询数量
     * @param example
     * @return
     */
    int countByExample(Example example);
    
}
