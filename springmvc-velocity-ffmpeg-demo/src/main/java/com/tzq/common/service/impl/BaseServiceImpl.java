package com.tzq.common.service.impl;

import com.tzq.common.dao.IBaseDao;
import com.tzq.common.domain.BaseTO;
import com.tzq.common.exception.DaoException;
import com.tzq.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
@Transactional
public class BaseServiceImpl implements BaseService {
	@Autowired
	private IBaseDao baseDao;

    @Override
	public <T extends BaseTO> void delete(T Model) throws DaoException {
		// TODO Auto-generated method stub
		baseDao.delete(Model);
	}

	@Override
	public <T extends BaseTO> void edit(T Model) throws DaoException {
		// TODO Auto-generated method stub
		baseDao.edit(Model);
	}

	@Override
	public <T extends BaseTO> void editSave(T Model) throws DaoException {
		// TODO Auto-generated method stub
		baseDao.editSave(Model);
	}

	@Override
	public <T extends BaseTO> void editUpdate(T Model) throws DaoException {
		// TODO Auto-generated method stub
		baseDao.editUpdate(Model);
	}

	@Override
	public <T extends BaseTO> List<T> queryAll(String hql) throws DaoException {
		// TODO Auto-generated method stub
		return baseDao.queryAll(hql);
	}


    @Override
    public <T extends BaseTO> Long countAll(Class<T> entityClass) {
        return baseDao.countAll(entityClass);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public <T extends BaseTO> List<T> listAll(Class<T> entityClass, int pn, int pageSize) {
        return baseDao.listAll(entityClass,pn,pageSize);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(String hsql) throws DaoException {

        baseDao.delete(hsql);
    }

    @Override
    public Integer execteNativeBulk(String sql) {
        return baseDao.execteNativeBulk(sql);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BaseTO> List<T> queryByNative(String sql,Class<T> entityClass) {
        return baseDao.queryByNative(sql, entityClass);
    }

    @Override
    public <T extends BaseTO> T get(Class<T> entityClass, Serializable id) {
        return baseDao.get(entityClass,id);
    }


}
