package com.tang.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.tang.dao.IBaseDao;
import com.tang.dao.util.Pagination;
import com.tang.dao.util.QueryCondition;
import org.springframework.stereotype.Repository;


@SuppressWarnings("unchecked")
@Repository("baseDao")
public class DefultBaseDao implements IBaseDao {
	
	@PersistenceContext
	protected EntityManager em;

	public <T> void delete(Class<T> clazz, Object id) {
		T entity = em.find(clazz, id);
		em.remove(entity);
	}

	public <T> void delete(Class<T> clazz, Object[] ids) {
		T entity = null;
		for (Object id : ids) {
			entity = em.find(clazz, id);
			em.remove(entity);
		}
	}

	public <T> List<T> getAll(Class<T> clazz) {
		String className = clazz.getSimpleName();
		StringBuffer jpql = new StringBuffer("select o from ");
		jpql.append(className).append(" o ");
		return em.createQuery(jpql.toString()).getResultList();
	}

	public <T> T getById(Class<T> clazz, Object id) {
		return em.find(clazz, id);
	}
	
	public <T> List<T> getByIds(Class<T> clazz, Object[] ids){
		List<T> list = new ArrayList<T>();
		for(Object id : ids){
			list.add(getById(clazz, id));
		}
		return list;
	}
	
	public void save(Object entity) {
		em.persist(entity);
	}

	public <T> void batchSave(List<T> entitys){
		for(T entity : entitys){
			em.persist(entity);
		}
	}
	
	public void update(Object entity) {
		em.merge(entity);
	}

	public <T> List<T> get(Class<T> clazz,
			List<QueryCondition> queryConditions, String orderBy,
			int currentPage, int pageSize) {
		Query query = getQuery(clazz, queryConditions, orderBy, false);
		if (currentPage == 0 && pageSize == 0) {
			return query.getResultList();
		} else {
			return query.setFirstResult((currentPage - 1) * pageSize)
					.setMaxResults(pageSize).getResultList();
		}

	}

	/**
	 * 根据查询条件获取Query
	 * 
	 * @param clazz
	 * @param queryConditions
	 * @param orderBy
	 * @param isQueryTotal
	 *            是否查询记录总数, true 则查询记录总数
	 * @return
	 */
	private Query getQuery(Class clazz, List<QueryCondition> queryConditions,
			String orderBy, boolean isQueryTotal) {
		String className = clazz.getSimpleName();
		String preJPQL = isQueryTotal ? "select count(*) from "
				: "select o from ";
		StringBuffer jpql = new StringBuffer(preJPQL);
		jpql.append(className).append(" o where 1=1 ");
		Query query = null;
		if (queryConditions != null && queryConditions.size() > 0) {
			// 构造jpql语句
			Iterator<QueryCondition> iterator = queryConditions.iterator();
			while (iterator.hasNext()) {
				QueryCondition queryCondition = iterator.next();
				if (queryCondition != null) {
					if (queryCondition.getOperator().equals(
							QueryCondition.CUSTOM)) {
						jpql.append(" and (").append(
								queryCondition.getCustomJPQL()).append(")");
					}
					if (queryCondition.getValue() != null
							&& !"".equals(queryCondition.getValue())) {
						// 如果占位符名称是*.*格式，则换成*_*格式。且：和名称之间不能有空格
						String placeholder = queryCondition.getField().indexOf(
								".") != -1 ? queryCondition.getField().replace(
								".", "_") : queryCondition.getField();
						jpql.append(" and o.").append(
								queryCondition.getField().trim()).append(" ")
								.append(queryCondition.getOperator()).append(
										":").append(placeholder.trim());
					}
				}

			}
		}
		if (orderBy != null && !"".equals(orderBy)) {
			jpql.append(" ").append(orderBy);
		}

		query = em.createQuery(jpql.toString());

		if (queryConditions != null && queryConditions.size() > 0) {
			// 为参数赋值
			Iterator<QueryCondition> iterator2 = queryConditions.iterator();
			while (iterator2.hasNext()) {
				QueryCondition queryCondition = iterator2.next();
				if (queryCondition != null) {
					if (queryCondition.getValue() != null
							&& !"".equals(queryCondition.getValue())) {
						// 将占位符中的.替换成_
						queryCondition.setField(queryCondition.getField()
								.indexOf(".") != -1 ? queryCondition.getField()
								.replace(".", "_") : queryCondition.getField());
						if (queryCondition.getOperator().equals(
								QueryCondition.LK)) {
							query.setParameter(queryCondition.getField(), "%"
									+ queryCondition.getValue() + "%");
						} else {
							query.setParameter(queryCondition.getField(),
									queryCondition.getValue());
						}
					}
				}

			}
		}
		return query;
	}

	public <T> List<T> get(Class<T> clazz, List<QueryCondition> queryConditions) {
		return get(clazz, queryConditions, null, 0, 0);
	}

	public <T> List<T> get(Class<T> clazz,
			List<QueryCondition> queryConditions, String orderBy) {
		return get(clazz, queryConditions, orderBy, 0, 0);
	}

	public Object getSingleResult(Class clazz,
			List<QueryCondition> queryConditions) {
		Query query = getQuery(clazz, queryConditions, null, false);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public long getRecordCount(Class clazz, List<QueryCondition> queryConditions) {
		Query query = getQuery(clazz, queryConditions, null, true);
		Object result;
		try {
			result = query.getSingleResult();
		} catch (NoResultException e) {
			result = null;
		}
		long recordCount = 0L;
		if (result != null) {
			recordCount = ((Long) result).longValue();
		}
		return recordCount;
	}

	public <T> List<T> getByJpql(String jpql, Object... objects) {
		Query query = em.createQuery(jpql);
		if (objects != null) {
			if (objects != null) {
				for (int i = 0; i < objects.length; i++) {
					query.setParameter(i + 1, objects[i]);
				}
			}
		}
		return query.getResultList();
	}
	
	public  Query createQuery(String jpql, Object... objects) {   
        Query query =em.createQuery(jpql);   
        if  (objects !=  null ) {   
            for  ( int  i =  0 ; i < objects.length; i++) {   
                query.setParameter(i+1, objects[i]);   
            }   
        }   
        return  query;   
    }   
	 public <T>List<T>  pageQuery(int currentPage, int pageSize,String jpql,Object... objects){
	  		@SuppressWarnings("unused")
			List<Object> list = null;
	  		Query query = this.createQuery(jpql, objects);
	  		if (currentPage == 0 && pageSize == 0) {
				return query.getResultList();
			} else {
				return query.setFirstResult((currentPage - 1) * pageSize)
						.setMaxResults(pageSize).getResultList();
			}
	  	}
	public int executeJpql(String jpql, Object... objects) {
		Query query = em.createQuery(jpql);
		if (objects != null) {
			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i + 1, objects[i]);
			}
		}
		return query.executeUpdate();
	}

	public <T> Pagination<T> getPagination(Class<T> clazz,
			List<QueryCondition> queryConditions, String orderBy,
			int currentPage, int pageSize) {
		List<T> recordList = get(clazz, queryConditions, orderBy, currentPage,
				pageSize);
		long recordCount = getRecordCount(clazz, queryConditions);
		return new Pagination(currentPage, pageSize, recordCount, recordList);
	}

	public Object getUniqueResultByJpql(String jpql, Object... objects) {
		Query query = em.createQuery(jpql);
		if (objects != null) {
			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i + 1, objects[i]);
			}
		}
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			// 如果查询出结果为null，JPA默认会抛NoResultException异常，我们让它返回null
			return null;
		}
	}

    public int executeBySQL(String sql, Object... params){
    	Query query = em.createNativeQuery(sql);
		if (params != null) {
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					query.setParameter(i + 1, params[i]);
				}
			}
		}
		return query.executeUpdate();
    }
	
	public <T>List<T> getPageQuery(int currentPage, int pageSize,
			String jpql, Object... objects) {
		List<T> recordList =pageQuery(currentPage, pageSize, jpql, objects);
		return recordList;
	}
	
}
