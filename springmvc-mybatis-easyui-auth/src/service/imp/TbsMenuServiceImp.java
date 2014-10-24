package service.imp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.TbsMenuService;
import dao.TbsMenuMapper;
/**
 * 
 * <br>
 * <b>功能：</b>用于事物处理<br>
 * <b>作者：</b>Wolf<br>
 * <b>日期：</b> 2013-4-9 <br>
 * <b>版权所有：<b>版权所有(C) 2011，QQ 405645010<br>
 */
@Service("tbsMenuService")
public class TbsMenuServiceImp<T> extends BaseServiceImp<T> implements TbsMenuService<T>{
	@Autowired
    private TbsMenuMapper<T> mapper;
		
	public TbsMenuMapper<T> getMapper() {
	    return mapper;
	}
	

	public java.util.List<java.util.Map<String,Object>> selectByMenuIsMenu(java.util.Map<String,Object> map) throws java.sql.SQLException{
		return mapper.selectByMenuIsMenu(map);
	}
	
	public java.util.List<java.util.Map<String,Object>> selectByMenuOther(java.util.Map<String,Object> map) throws java.sql.SQLException{
		return mapper.selectByMenuOther(map);
	}

}
