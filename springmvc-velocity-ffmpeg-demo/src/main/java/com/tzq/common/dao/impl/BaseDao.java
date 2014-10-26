package com.tzq.common.dao.impl;

import com.tzq.common.dao.IBaseDao;
import com.tzq.common.domain.BaseTO;
import com.tzq.common.exception.DaoException;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public class BaseDao implements IBaseDao {
	
    @Autowired
	private SessionFactory sessionFactory;

	/**
	 * 保存实例
	 * 
	 * @param Model
	 * @throws DaoException
	 */
	public <T extends BaseTO> void editSave(T Model) throws DaoException {
		try {
			sessionFactory.getCurrentSession().save(Model);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(this.getClass(), e.getMessage());
		}
	}

	/**
	 * 修改实例
	 * 
	 * @param Model
	 * @throws DaoException
	 */
	public <T extends BaseTO> void editUpdate(T Model) throws DaoException {
		try {
			sessionFactory.getCurrentSession().update(Model);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(this.getClass(), e.getMessage());
		}
	}

	/**
	 * 保存或修改实例
	 * 
	 * @param Model
	 * @throws DaoException
	 */
	public <T extends BaseTO> void edit(T Model) throws DaoException {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(Model);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(this.getClass(), e.getMessage());
		}
	}

	/**
	 * 删除实例
	 * 
	 * @param Model
	 * @throws DaoException
	 */
	public <T extends BaseTO> void delete(T Model) throws DaoException {
		try {
			sessionFactory.getCurrentSession().delete(Model);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(this.getClass(), e.getMessage());
		}
	}

    @SuppressWarnings("unchecked")
	@Override
	public <T extends BaseTO> List<T> queryAll(String hql) throws DaoException {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}

    @Override
    public <T extends BaseTO> Long countAll(Class<T> entityClass) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entityClass);
        criteria.setProjection(Projections.rowCount());
        return (Long)criteria.uniqueResult() ;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BaseTO> List<T> listAll(Class<T> entityClass, int pn, int pageSize) {

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entityClass);
        criteria.setFirstResult((pn-1)*pageSize);
        criteria.setFetchSize(pageSize);
        return criteria.list();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(String hsql) throws DaoException {
        sessionFactory.getCurrentSession().createQuery(hsql).executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Integer execteNativeBulk(String sql) {
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        Object result = query.executeUpdate();
        return result == null ? 0 : ((Integer) result);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BaseTO> List<T> queryByNative(String sql, Class<T> entityClass) {
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(entityClass);
        return query.list();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BaseTO> T get(Class<T> entityClass, Serializable id) {
        return (T)sessionFactory.getCurrentSession().get(entityClass,id);
    }


}
