package com.tang.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.tang.dao.IBaseDao;
import com.tang.dao.util.Pagination;
import com.tang.dao.util.QueryCondition;
import com.tang.service.IBaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("unchecked")
@Service("baseService")
public class DefultBaseService implements IBaseService {
	
	@Resource(name="baseDao")
	private IBaseDao baseDao;

	@Transactional(propagation=Propagation.REQUIRED)
	public <T> void delete(Class<T> clazz, Object id) {
		baseDao.delete(clazz, id);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public <T> void delete(Class<T> clazz, Object[] ids) {
		baseDao.delete(clazz, ids);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public int executeJpql(String jpql, Object... objects) {
		return baseDao.executeJpql(jpql, objects);
	}

	public <T> List<T> get(Class<T> clazz,
			List<QueryCondition> queryConditions, String orderBy,
			int currentPage, int pageSize) {
		return baseDao.get(clazz, queryConditions, orderBy, currentPage, pageSize);
	}

	public <T> List<T> get(Class<T> clazz, List<QueryCondition> queryConditions) {
		return baseDao.get(clazz, queryConditions);
	}

	public <T> List<T> get(Class<T> clazz,
			List<QueryCondition> queryConditions, String orderBy) {
		return baseDao.get(clazz, queryConditions, orderBy);
	}

	public <T> List<T> getAll(Class<T> clazz) {
		return baseDao.getAll(clazz);
	}

	public <T> T getById(Class<T> clazz, Object id) {
		return baseDao.getById(clazz, id);
	}

	public <T> List<T> getByIds(Class<T> clazz, Object[] ids){
		return baseDao.getByIds(clazz, ids);
	}
	
	public <T> List<T> getByJpql(String jpql, Object... objects) {
		return baseDao.getByJpql(jpql, objects);
	}

	public <T> Pagination<T> getPagination(Class<T> clazz,
			List<QueryCondition> queryConditions, String orderBy,
			int currentPage, int pageSize) {
		return baseDao.getPagination(clazz, queryConditions, orderBy, currentPage, pageSize);
	}
	
	public <T>List<T>  getPaginationJpql(int currentPage, int pageSize,String jpql,Object... objects) {
		return baseDao.getPageQuery(currentPage, pageSize, jpql, objects);
	}
	public long getRecordCount(Class clazz, List<QueryCondition> queryConditions) {
		return baseDao.getRecordCount(clazz, queryConditions);
	}

	public Object getSingleResult(Class clazz,
			List<QueryCondition> queryConditions) {
		return baseDao.getSingleResult(clazz, queryConditions);
	}

	public Object getUniqueResultByJpql(String jpql, Object... objects) {
		return baseDao.getUniqueResultByJpql(jpql, objects);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void save(Object entity) {
		baseDao.save(entity);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public <T> void batchSave(List<T> entitys){
		baseDao.batchSave(entitys);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void update(Object entity) {
		baseDao.update(entity);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public int executeBySQL(String sql, Object... params){
		return baseDao.executeBySQL(sql, params);
	}
	
}
