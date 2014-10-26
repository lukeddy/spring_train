package service;

import dao.BaseMapper;

/**
 * 
 * <br>
 * <b>功能：</b>定义在这里由 BaseServiceImp来实现通用的<br>
 * <b>作者：</b>Wolf<br>
 * <b>日期：</b> 2013-5-24 <br>
 * <b>版权所有：<b>版权所有(C) 2011，QQ 405645010<br>
 */
public interface BaseService<T> extends BaseMapper<T>{
	/**
	 * 
	 * <br>
	 * <b>功能：</b>删除多条记录<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-5-24 <br>
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public Integer deleteByPrimaryKeys(Object... keys) throws Exception ;
}
