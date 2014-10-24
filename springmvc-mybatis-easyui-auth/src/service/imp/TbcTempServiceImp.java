package service.imp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.TbcTempService;
import dao.TbcTempMapper;
/**
 * 
 * <br>
 * <b>功能：</b>用于事物处理<br>
 * <b>作者：</b>Wolf<br>
 * <b>日期：</b> 2013-4-9 <br>
 * <b>版权所有：<b>版权所有(C) 2011，QQ 405645010<br>
 */
@Service("tbcTempService")
public class TbcTempServiceImp<T> extends BaseServiceImp<T> implements TbcTempService<T>{
	@Autowired
    private TbcTempMapper<T> mapper;
		
	public TbcTempMapper<T> getMapper() {
	    return mapper;
	}
	

}
