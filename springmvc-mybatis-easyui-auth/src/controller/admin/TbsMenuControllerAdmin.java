
package controller.admin;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import bean.TbsMenuBean;
import bean.model.TbsMenuModel;
import service.TbsMenuService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import util.spring.MyTimestampPropertyEdit;
import util.core.MethodUtil;
import util.json.JSONUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import service.TbsRoleMenuService;
import bean.TbsRoleMenuBean;
@Controller
@RequestMapping("/admin/TbsMenu")
public class TbsMenuControllerAdmin extends BaseController{	private final static Logger log= Logger.getLogger(TbsMenuControllerAdmin.class);
	private static  MethodUtil util = new MethodUtil();
	// 服务类
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private TbsMenuService<TbsMenuBean> tbsMenuService; 
	
	//参数类
	private List<TbsMenuBean> listTbsMenu = null;
	private TbsMenuBean tbsMenuBean = new TbsMenuBean();  //JavaBean
	//private TbsMenuModel tbsMenuModel=new TbsMenuModel(); //分页模型
	
		@InitBinder//必须有一个参数WebDataBinder 日期类型装换
	public void initBinder(WebDataBinder binder) {
		    binder.registerCustomEditor(Timestamp.class,new MyTimestampPropertyEdit()); //使用自定义属性编辑器
		//  binder.registerCustomEditor(Date.class,new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}	/**
	 * 
	 * <br>
	 * <b>功能：</b>主页<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-25 <br>
	 * @return
	 */
	@RequestMapping("/index.html")
	public ModelAndView list(/*TbsMenuModel tbsMenuModel*/){
		return new ModelAndView("admin/TbsMenu/TbsMenuIndex");
	}	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>查询方法<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-5-12 <br>
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @param tbsMenuChildModel
	 * @param searchAnds
	 * @param searchColumnNames
	 * @param searchConditions
	 * @param searchVals
	 * @return
	 */
	@RequestMapping("/listData.html")
	@ResponseBody
	public String listData(String page,String rows,String sort,String order,TbsMenuModel tbsMenuModel,String searchAnds,String searchColumnNames,String searchConditions,String searchVals){
		String result = "[]";
		System.out.println("page:"+page+"|rows:"+rows+"|sort:"+sort+"|order:"+order+"TbsMenuModel:"+tbsMenuModel+"|searchAnds:"+searchAnds+"|searchColumnNames:"+searchColumnNames);
		if(null!=searchColumnNames && searchColumnNames.trim().length()>0){
			StringBuffer sb=new StringBuffer();
			String[] searchColumnNameArray=searchColumnNames.split("\\,");
			String[] searchAndsArray=searchAnds.split("\\,");
			String[] searchConditionsArray=searchConditions.split("\\,");
			String[] searchValsArray=searchVals.split("\\,");
			System.out.println(Arrays.toString(searchColumnNameArray));
			for (int i = 0; i < searchColumnNameArray.length; i++) {
				if (searchColumnNameArray[i].trim().length() > 0 && searchConditionsArray[i].trim().length()>0) {
					if (i == 0) {
						sb.append(searchColumnNameArray[i].trim() + " " + searchConditionsArray[i].trim() + " " + searchValsArray[i].trim());
					} else {
						sb.append(" " + searchAndsArray[i].trim() + " " + searchColumnNameArray[i].trim() + " " + searchConditionsArray[i].trim() + " " + searchValsArray[i].trim());
					}
				}
			}
			if(sb.length()>0){
				System.out.println("sb:"+sb.toString());
				Map<String,Object> map=new HashMap<String, Object>();
				util.core.PageUtil pageUtil=new util.core.PageUtil();
				pageUtil.setAndCondition(sb.toString());
				pageUtil.setPageId(Integer.parseInt(page));
				pageUtil.setPageSize(Integer.parseInt(rows));
				map.put("pageUtil", pageUtil);
				try {
					listTbsMenu = tbsMenuService.selectByMapPaging(map);
					result="{\"total\":\""+pageUtil.getRowCount()+"\",\"rows\":"+com.alibaba.fastjson.JSON.toJSONString(listTbsMenu)+"}";
				} catch (Exception e) {
					e.printStackTrace();
				}
				return result;
			}
		}
		try{
			tbsMenuModel.getPageUtil().setPageId(Integer.parseInt(page)); //当前页
			tbsMenuModel.getPageUtil().setPageSize(Integer.parseInt(rows));//显示X条
			//tbsMenuModel.getPageUtil().setOrderField(sort);      //排序字段名称
			//tbsMenuModel.getPageUtil().setOrderDirection(true);  //true false 表示 正序与倒序
			listTbsMenu = tbsMenuService.selectByModel(tbsMenuModel);
			result="{\"total\":\""+tbsMenuModel.getPageUtil().getRowCount()+"\",\"rows\":"+com.alibaba.fastjson.JSON.toJSONString(listTbsMenu)+"}";
		}catch(Exception e){
			log.error(e);
		}
		return result;
	}
    /**
     * 
     * <br>
     * <b>功能：</b>转向<br>
     * <b>作者：</b>wolf<br>
     * <b>日期：</b> 2013-1-22 <br>
     * @param ids
     * @param map
     * @return
     */
	@RequestMapping("/edit.html") 
	public String edit(){
		return "admin/TbsMenu/TbsMenuEdit";
	}
	    /**
     * 
     * <br>
     * <b>功能：</b>增加 TbsMenuBean<br>
     * <b>作者：</b>wolf<br>
     * <b>日期：</b> 2012-10-11 <br>
     * @return
     */
	@RequestMapping("/add.html")
	public void add(TbsMenuBean tbsMenuBean,HttpServletResponse response){
		tbsMenuBean.setId(util.getUid().toString());//设置主键
		System.out.println("tbsMenuBean:"+tbsMenuBean.toString());
	    try {
	    	if(null!=tbsMenuBean.getParentId() && tbsMenuBean.getParentId().trim().length()==0){
	    		tbsMenuBean.setParentId(null);
	    	}
			if(tbsMenuService.insert(tbsMenuBean)>0){
				util.toJsonMsg(response, 0, null);
				return;
			};
		} catch (Exception e) {
			e.printStackTrace();
		}
	    util.toJsonMsg(response, 1, null);
	}    /**
     * 
     * <br>
     * <b>功能：</b>保存 TbsMenuBean<br>
     * <b>作者：</b>wolf<br>
     * <b>日期：</b> 2012-10-11 <br>
     * @return
     */
	@RequestMapping("/save.html") 
	public void save(TbsMenuBean tbsMenuBean,HttpServletResponse response){
		try{
	    	if(null!=tbsMenuBean.getParentId() && tbsMenuBean.getParentId().trim().length()==0){
	    		tbsMenuBean.setParentId(null);
	    	}
		 	if(tbsMenuService.updateByPrimaryKey(tbsMenuBean)>0){
			    util.toJsonMsg(response, 0, null);
			    return;
			 }
		}catch(Exception e){
			util.toJsonMsg(response, 1, null);
			e.printStackTrace();
		}
	}
	    /**
     * 
     * <br>
     * <b>功能：</b>删除 TbsMenuBean<br>
     * <b>作者：</b>肖财高<br>
     * <b>日期：</b> 2013-4-25 <br>
     * @param ids
     * @param response
     */
	@RequestMapping("/del.html") 
	public void del(String[] ids,HttpServletResponse response){
		System.out.println("del-ids:"+Arrays.toString(ids));
		try{
			 if(null!=ids && ids.length>0){
				 if(tbsMenuService.deleteByPrimaryKeys(ids)>0){
					 util.toJsonMsg(response, 0, null);
					 return;
				 }
			 }
		}catch(Exception e){
			util.toJsonMsg(response, 1, null);
			log.error(e);
		}
	}
		
	@RequestMapping("gridTree.html")
	@ResponseBody
	public String gridTree(HttpServletResponse response, String id,String name,String value) throws Exception {
		List<Map<String, Object>> lm;
		Map<String,Object> map=new HashMap<String, Object>();
		if (null == id && null==name) {
			map.put("andCondition", "parentId IS NULL");
			lm=tbsMenuService.selectByMenuOther(map);
		} else {
			if(null != id){
				map.put("parentId", id);
				lm = tbsMenuService.selectByMenuOther(map);
			}else{
				map.put(name, value);
				lm = tbsMenuService.selectByMenuOther(map);
			}
		}
		map.clear();
		if (lm != null && lm.size() > 0) {
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			for (int i = 0; i < lm.size(); i++) {
				sb.append("{");
				map = lm.get(i);
				Long child = (Long) map.get("child");
				String menuId = (String) map.get("id");
				if (child > 0 ) {
					sb.append("\"state\":\"closed\",");
				}
				sb.append("\"id\":" + "\"" + menuId + "\",");
				sb.append("\"url\":" + "\"" + map.get("url") + "\",");
				sb.append("\"createTime\":" + "\"" + map.get("createTime") + "\",");
				sb.append("\"parentId\":" + "\"" + map.get("parentId") + "\",");
				sb.append("\"type\":" + "\"" + map.get("type") + "\",");
				sb.append("\"sortNumber\":" + "\"" + map.get("sortNumber") + "\",");
				sb.append("\"parent\":" + "\"" + map.get("parent") + "\",");
				sb.append("\"child\":" + "\"" + map.get("child") + "\",");
				sb.append("\"isMenu\":" + "\"" + map.get("isMenu") + "\",");
				sb.append("\"name\":" + "\"" + map.get("name") + "\"");
				sb.append("},");
			}
			sb = sb.delete(sb.length() - 1, sb.length());
			sb.append("]");
			System.out.println("json:" + sb.toString());
			return sb.toString();
		}
		return "[]";
	}
	
	@Autowired
	TbsRoleMenuService<TbsRoleMenuBean> tbsRoleMenuService;
	@RequestMapping("comboxTree.html")
	@ResponseBody
	public String comboxTree(HttpServletResponse response, String id, String roleId) throws Exception {
		List<Map<String, Object>> lm;
		Map<String,Object> map=new HashMap<String, Object>();
		//TbsRoleMenuBean tbsRoleMenuBean = new TbsRoleMenuBean();
		if (null == id) {
			map.put("andCondition", "parentId IS NULL");
			lm = tbsMenuService.selectByMenuOther(map);
		} else {
			map.put("parentId", id);
			lm = tbsMenuService.selectByMenuOther(map);
		}
		map.clear();
		if (lm != null && lm.size() > 0) {
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			for (int i = 0; i < lm.size(); i++) {
				sb.append("{");
				map = lm.get(i);
				Long child = (Long) map.get("child");
				String menuId = (String) map.get("id");
				if (roleId != null) {
					TbsRoleMenuBean tbsRoleMenuBean=new TbsRoleMenuBean();
					tbsRoleMenuBean.setRoleId(roleId);
					tbsRoleMenuBean.setMenuIdFun(menuId);
					if (tbsRoleMenuService.selectByEntiry(tbsRoleMenuBean).size() > 0) {
						sb.append("\"checked\":true,");
					}
				}
				// Integer parent=(Integer) map.get("parent");
				if (child > 0 ) {
					sb.append("\"state\":\"closed\",");
				}
				sb.append("\"id\":" + "\"" + menuId + "\",");
				sb.append("\"text\":" + "\"" + map.get("name") + "\"");
				sb.append("},");
			}
			sb = sb.delete(sb.length() - 1, sb.length());
			sb.append("]");
			System.out.println("json:" + sb.toString());
			return sb.toString();
		}
		return "[]";
	}
	
}

