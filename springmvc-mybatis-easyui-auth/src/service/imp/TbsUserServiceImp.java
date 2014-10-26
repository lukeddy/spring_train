package service.imp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.TbsUserService;
import dao.TbsUserMapper;
/**
 * 
 * <br>
 * <b>功能：</b>用于事物处理<br>
 * <b>作者：</b>Wolf<br>
 * <b>日期：</b> 2013-4-9 <br>
 * <b>版权所有：<b>版权所有(C) 2011，QQ 405645010<br>
 */
@Service("tbsUserService")
public class TbsUserServiceImp<T> extends BaseServiceImp<T> implements TbsUserService<T>{
	@Autowired
    private TbsUserMapper<T> mapper;
		
	public TbsUserMapper<T> getMapper() {
	    return mapper;
	}
	

	public java.util.List<java.util.Map<String,Object>> selectByRoleUrls(java.util.Map<?,?> map) throws Exception{
	    return mapper.selectByRoleUrls(map);
    }
}
