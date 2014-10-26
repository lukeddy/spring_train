package controller.view;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import util.json.JSONUtil;

/**
 * <br>
 * <b>功能：</b>类功能描述<br>
 * <b>作者：</b>Wolf<br>
 * <b>日期：</b> 2012-6-18 <br>
 * <b>版权所有：<b>版权所有(C) 2012 QQ 405645010<br>
 * <b>更新者：</b><br>
 * <b>日期：</b> <br>
 * <b>更新内容：</b><br>
 */
public class BaseController extends MultiActionController{
	protected final Logger log = Logger.getLogger(BaseController.class);
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>输出JSON<br>
	 * <b>作者：</b>wolf<br>
	 * <b>日期：</b> 2012-6-18 <br>
	 * @param t
	 * @param response
	 */
	public <T> void toJson(T t,HttpServletResponse response){
		 try {
			PrintWriter pw=response.getWriter();
			pw.write(JSONUtil.toJSONString(t));
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e);
		}
	}
}

