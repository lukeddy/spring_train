package bean.model;
import util.core.PageUtil;
/**
 * 
 * <br>
 * <b>功能：</b>模型-处理分页查询功能<br>
 * <b>作者：</b>Wolf<br>
 * <b>日期：</b> 2012-7-30 <br>
 * <b>版权所有：<b>版权所有(C) 2011，QQ 405645010<br>
 */
public class BaseModel {

    /**
     * 
     * <br>
     * <b>功能：</b>分页实现<br>
     * <b>作者：</b>肖财高<br>
     * <b>日期：</b> 2013-5-23 <br>
     * @param navigate
     */
	private PageUtil pageUtil=new PageUtil();

	public PageUtil getPageUtil() {
		return pageUtil;
	}
	public void setPageUtil(PageUtil pageUtil) {
		this.pageUtil = pageUtil;
	}
	
}
