package service.imp;

import java.util.List;
import java.util.Map;
import dao.BaseMapper;
import bean.model.BaseModel;
import service.BaseService;

/**
 * 
 * <br>
 * <b>功能：</b>基础实现<br>
 * <b>作者：</b>Wolf<br>
 * <b>日期：</b> 2013-5-24 <br>
 * <b>版权所有：<b>版权所有(C) 2011，QQ 405645010<br>
 */
public class BaseServiceImp<T> implements BaseService<T>{

	private BaseMapper<T> mapper;
	/**
	 * 
	 * <br>
	 * <b>功能：</b>子类重写方法<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-9 <br>
	 * @return
	 */
	public BaseMapper<T> getMapper(){
		return mapper;
	}
	 /**
     * 
     * <br>
     * <b>功能：</b>主键查询<br>
     * <b>作者：</b>肖财高<br>
     * <b>日期：</b> 2013-4-9 <br>
     * @param key
     * @return
     * @throws Exception
     */
	public T selectByPrimaryKey(Object key) throws Exception {
		return getMapper().selectByPrimaryKey(key);
	}
    /**
     * 
     * <br>
     * <b>功能：</b>主键修改<br>
     * <b>作者：</b>肖财高<br>
     * <b>日期：</b> 2013-4-9 <br>
     * @param t
     * @throws Exception
     */
	public Integer updateByPrimaryKey(T t) throws Exception {
		return getMapper().updateByPrimaryKey(t);
	}
    /**
     * 
     * <br>
     * <b>功能：</b>主键删除<br>
     * <b>作者：</b>肖财高<br>
     * <b>日期：</b> 2013-4-9 <br>
     * @param keys
     * @throws Exception
     */
	public Integer deleteByPrimaryKeys(Object... keys) throws Exception {
		int i=0;
		for(Object key:keys){
			i+=getMapper().deleteByPrimaryKey(key);
		}
		return i;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>插入<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-9 <br>
	 * @param t
	 * @throws Exception
	 */
	public Integer insert(T t) throws Exception {
		return getMapper().insert(t);
	}
    /**
     * 
     * <br>
     * <b>功能：</b>查询总行数<br>
     * <b>作者：</b>肖财高<br>
     * <b>日期：</b> 2013-4-9 <br>
     * @param model
     * @return
     * @throws Exception
     */
	public Integer selectByModelCount(BaseModel model) throws Exception {
		return getMapper().selectByModelCount(model);
	}
    /**
     * 
     * <br>
     * <b>功能：</b>查询Map总行数<br>
     * <b>作者：</b>肖财高<br>
     * <b>日期：</b> 2013-4-9 <br>
     * @param map
     * @return
     * @throws Exception
     */
	public Integer selectByMapCount(Map<?, ?> map) throws Exception {
		return getMapper().selectByMapCount(map);
	}
    /**
     * 
     * <br>
     * <b>功能：</b>模型分页<br>
     * <b>作者：</b>肖财高<br>
     * <b>日期：</b> 2013-4-9 <br>
     * @param model
     * @return
     * @throws Exception
     */
	public List<T> selectByModel(BaseModel model) throws Exception {
		model.getPageUtil().setRowCount(selectByModelCount(model));
		return getMapper().selectByModel(model);
	}
    /**
     * 
     * <br>
     * <b>功能：</b>Map查询 <br>
     * <b>作者：</b>肖财高<br>
     * <b>日期：</b> 2013-4-9 <br>
     * @param map
     * @return
     * @throws Exception
     */
	public List<T> selectByMap(Map<?, ?> map) throws Exception {
		return getMapper().selectByMap(map);
	}
  
	/**
	 * 
	 * <br>
	 * <b>功能：</b>实体查询<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-11 <br>
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public List<T> selectByEntiry(T entity) throws Exception{
		return getMapper().selectByEntiry(entity);
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>实体删除<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-11 <br>
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public Integer deleteByEntity(T entity) throws Exception{
		  return getMapper().deleteByEntity(entity);
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>SQL查询<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-11 <br>
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public List<T> selectBySql(String sql) throws Exception{
		return getMapper().selectBySql(sql);
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>SQL修改<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-11 <br>
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public Integer updateBySql(String sql) throws Exception{
		return getMapper().updateBySql(sql);
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>SQL删除<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-11 <br>
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public Integer deleteBySql(String sql) throws Exception{
		return getMapper().deleteBySql(sql);
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>SQL增加<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-11 <br>
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public Integer insertBySql(String sql) throws Exception{
		return getMapper().insertBySql(sql);
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>查询分页数<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-5-11 <br>
	 * @param map
	 * @return
	 * @throws java.sql.SQLException
	 */
	public Integer selectByMapPagingCount(Map<?,?> map) throws java.sql.SQLException{
		return getMapper().selectByMapPagingCount(map);
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>查询分页<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-5-11 <br>
	 * @param map
	 * @return
	 * @throws java.sql.SQLException
	 */
	public List<T> selectByMapPaging(Map<?,?> map) throws java.sql.SQLException{
		int rowCount = getMapper().selectByMapPagingCount(map);
		util.core.PageUtil pageUtil=(util.core.PageUtil) map.get("pageUtil");
		if(null==pageUtil){
			System.out.println("错误!!!  pageUtil 参数为NULL");
			return null;
		}
		pageUtil.setRowCount(rowCount);
		return getMapper().selectByMapPaging(map);
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>删除一条记录<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-5-24 <br>
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public Integer deleteByPrimaryKey(Object key) throws Exception {
		return getMapper().deleteByPrimaryKey(key);
	}
}
