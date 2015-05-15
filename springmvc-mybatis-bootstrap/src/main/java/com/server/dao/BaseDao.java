package com.server.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

public class BaseDao extends SqlSessionDaoSupport {

	public <T> T get(String key, Object param) {
		return getSqlSession().selectOne(key, param);
	}

	public <T> List<T> getList(String key, Object param) {
		return getSqlSession().selectList(key, param);
	}

	public void insert(String key, Object param) {
		getSqlSession().insert(key, param);
	}
	
	public void update(String key, Object param) {
		getSqlSession().update(key, param);
	}
	
	public void delete(String key, Object param) {
		getSqlSession().delete(key, param);
	}

}
