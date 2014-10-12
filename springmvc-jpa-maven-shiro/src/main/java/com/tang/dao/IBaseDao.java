package com.tang.dao;

import java.util.List;

import com.tang.dao.util.Pagination;
import com.tang.dao.util.QueryCondition;

public interface IBaseDao {
	/**
     * 新增实体
     * @param entity  要新增的实体
     */
    public void save(Object entity);
    
    /**
     * 批量创建
     * @param <T>
     * @param entitys
     */
    public <T> void batchSave(List<T> entitys);
    
    
    /**
     * 更新实体
     * @param entity  要更新的实体
     */
    public void update(Object entity);
    
    /**
     * 执行SQL语句
     * @param sql
     * @param params
     * @return
     */
    public int executeBySQL(String sql, Object... params);
    
    /**
     * 根据主键删除实体
     * @param <T>
     * @param clazz   实体类的Class
     * @param id      主键
     */
    public <T> void delete(Class<T> clazz,Object id);
    
    /**
     * 根据主键批量删除实体
     * @param <T>
     * @param clazz   实体类的Class
     * @param ids      主键数组
     */
    public <T> void delete(Class<T> clazz,Object[] ids);
    
    /**
     * 根据主键查询
     * @param <T>
     * @param clazz  实体类的Class
     * @param id     主键
     * @return
     */
    public <T> T getById(Class<T> clazz,Object id);
    
    /**
     * 根据主键数组查询
     * @param <T>
     * @param clazz	 实体类的Class
     * @param ids	 主键数组
     * @return
     */
    public <T> List<T> getByIds(Class<T> clazz, Object[] ids);
    
    /**
     * 查询所有记录
     * @param <T>
     * @param clazz 实体类的Class
     * @return
     */
    public <T> List<T> getAll(Class<T> clazz);
    
    
    /**
     * 根据条件集合查询记录
     * @param <T>
     * @param clazz
     * @param queryConditions 查询条件集合
     * @param orderBy         排序,如 order by id desc
     * @param currentPage     当前页
     * @param pageSize        每页显示记录数
     * @return 
     */
    public <T> List<T> get(Class<T> clazz,List<QueryCondition> queryConditions,String orderBy,int currentPage,int pageSize);
    
    
    /**
     * 根据条件集合查询记录
     * @param <T>
     * @param clazz
     * @param queryConditions  查询条件集合
     * @return
     */
    public <T> List<T> get(Class<T> clazz,List<QueryCondition> queryConditions);
    
    /**
     * 根据条件集合查询记录
     * @param <T>
     * @param clazz
     * @param queryConditions  查询条件集合
     * @param orderBy          排序,如 order by id desc
     * @return
     */
    public <T> List<T> get(Class<T> clazz,List<QueryCondition> queryConditions,String orderBy);
    
    /**
     * 根据条件集合查询单条记录
     * @param clazz
     * @param queryConditions  查询条件集合
     * @return
     */
    
    @SuppressWarnings("unchecked")
	public Object getSingleResult(Class clazz,List<QueryCondition> queryConditions);
    
    /**
     * 根据条件查询记录数量
     * @param clazz
     * @param queryConditions  查询条件集合
     * @return
     */
    @SuppressWarnings("unchecked")
    public long getRecordCount(Class clazz,List<QueryCondition> queryConditions);
    
    /**
     * 根据jpql查询
     * @param <T>
     * @param jpql
     * @param objects
     * @return
     */
    public <T> List<T> getByJpql(String jpql,Object...objects);
    
    /**
     * 执行jpql语句
     * @param jpql
     * @param objects
     * @return
     */
    public int executeJpql(String jpql,Object... objects);
    
    /**
     * 分页查询
     * @param <T>
     * @param clazz
     * @param queryConditions   查询条件集合
     * @param orderBy           排序字段 如：order by id desc
     * @param currentPage       当前页
     * @param pageSize          每页显示记录数
     * @return
     */
    public <T> Pagination<T> getPagination(Class<T> clazz,List<QueryCondition> queryConditions,String orderBy,int currentPage,int pageSize);
    
    /**
     *   根据JPQL分页查询
     * @param currentPage
     * @param pageSize
     * @param jpql
     * @param objects
     * @return
     */
    public <T>List<T> getPageQuery(int currentPage, int pageSize,String jpql,Object... objects);
    
    /**
     * 查找唯一结果
     * @param jpql
     * @param objects
     * @return
     */
    public Object getUniqueResultByJpql(String jpql,Object...objects);
     
}
