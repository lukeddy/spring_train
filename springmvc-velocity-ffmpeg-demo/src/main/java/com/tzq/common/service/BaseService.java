package com.tzq.common.service;

import com.tzq.common.domain.BaseTO;
import com.tzq.common.exception.DaoException;

import java.io.Serializable;
import java.util.List;

public interface BaseService {

	/**
	 * 保存实例
	 * 
	 * @param Model
	 * @throws DaoException
	 */
	public <T extends BaseTO> void editSave(T Model) throws DaoException;
	
	/**
	 * 修改实例
	 * 
	 * @param Model
	 * @throws DaoException
	 */
	public <T extends BaseTO> void editUpdate(T Model) throws DaoException;
	
	/**
	 * 保存或修改实例
	 * 
	 * @param Model
	 * @throws DaoException
	 */
	public <T extends BaseTO> void edit(T Model) throws DaoException;

	/**
	 * 删除实例
	 * 
	 * @param Model
	 * @throws DaoException
	 */
	public <T extends BaseTO> void delete(T Model) throws DaoException;
	public <T extends BaseTO> List<T> queryAll(String hql) throws DaoException;

    public <T extends BaseTO> Long countAll(Class<T> entityClass);

    public <T extends BaseTO> List<T> listAll(Class<T> entityClass, int pn, int pageSize);

    public void delete(String hsql) throws DaoException;

    public  Integer execteNativeBulk(String sql);

    public <T extends BaseTO> List<T> queryByNative(String sql, Class<T> entityClass);

    public <T extends BaseTO> T get(Class<T> entityClass, Serializable id );
}
