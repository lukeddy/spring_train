package controller.admin;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import bean.TbsRoleBean;
import bean.model.TbsRoleModel;
import service.TbsRoleService;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import service.TbsMenuService;
import service.TbsRoleMenuService;
import service.TbsRoleUserService;
import bean.TbsMenuBean;
import bean.TbsRoleBean;
import bean.TbsRoleMenuBean;
import bean.TbsRoleUserBean;
import service.TbsMenuService;
import service.TbsRoleMenuService;
import service.TbsRoleUserService;
import bean.TbsMenuBean;
import bean.TbsRoleMenuBean;
import bean.TbsRoleUserBean;
@Controller
@RequestMapping("/admin/TbsRole")
public class TbsRoleControllerAdmin extends BaseController{	private final static Logger log= Logger.getLogger(TbsRoleControllerAdmin.class);
	private static  MethodUtil util = new MethodUtil();
	// 服务类
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private TbsRoleService<TbsRoleBean> tbsRoleService; 
	
	//参数类
	private List<TbsRoleBean> listTbsRole = null;
	private TbsRoleBean tbsRoleBean = new TbsRoleBean();  //JavaBean
	//private TbsRoleModel tbsRoleModel=new TbsRoleModel(); //分页模型
	
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
	public ModelAndView list(/*TbsRoleModel tbsRoleModel*/){
		return new ModelAndView("admin/TbsRole/TbsRoleIndex");
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
	public String listData(String page,String rows,String sort,String order,TbsRoleModel tbsRoleModel,String searchAnds,String searchColumnNames,String searchConditions,String searchVals){
		String result = "[]";
		System.out.println("page:"+page+"|rows:"+rows+"|sort:"+sort+"|order:"+order+"TbsRoleModel:"+tbsRoleModel+"|searchAnds:"+searchAnds+"|searchColumnNames:"+searchColumnNames);
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
					listTbsRole = tbsRoleService.selectByMapPaging(map);
					result="{\"total\":\""+pageUtil.getRowCount()+"\",\"rows\":"+com.alibaba.fastjson.JSON.toJSONString(listTbsRole)+"}";
				} catch (Exception e) {
					e.printStackTrace();
				}
				return result;
			}
		}
		try{
			tbsRoleModel.getPageUtil().setPageId(Integer.parseInt(page)); //当前页
			tbsRoleModel.getPageUtil().setPageSize(Integer.parseInt(rows));//显示X条
			//tbsRoleModel.getPageUtil().setOrderField(sort);      //排序字段名称
			//tbsRoleModel.getPageUtil().setOrderDirection(true);  //true false 表示 正序与倒序
			listTbsRole = tbsRoleService.selectByModel(tbsRoleModel);
			result="{\"total\":\""+tbsRoleModel.getPageUtil().getRowCount()+"\",\"rows\":"+com.alibaba.fastjson.JSON.toJSONString(listTbsRole)+"}";
		}catch(Exception e){
			log.error(e);
		}
		return result;
	}
    
	///////////////////////////////////在下面添加自定义的方法///////////////////////////////////
	@Autowired
	TbsMenuService<TbsMenuBean> tbsMenuService;
	@Autowired
	TbsRoleMenuService<TbsRoleMenuBean> tbsRoleMenuService;
	@Autowired
	TbsRoleUserService<TbsRoleUserBean> tbsRoleUserService;

	/**
	 * 
	 * <br>
	 * <b>功能：</b>增加操作 TbsRoleBean<br>
	 * <b>作者：</b>wolf<br>
	 * <b>日期：</b> 2012-10-11 <br>
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/add.html")
	public void add(HttpServletResponse response,TbsRoleBean tbsRoleBean, String... roleAuthTree) {
		String roleId = util.getUidString();
		TbsRoleMenuBean tbsRoleMenuBean = new TbsRoleMenuBean();
		try {
			if (null != roleAuthTree) {
				Map<String,Object> map=new HashMap<String, Object>();
				for (int i = 0; i < roleAuthTree.length; i++) {
					tbsRoleMenuBean.setId(util.getUidString());
					tbsRoleMenuBean.setRoleId(roleId);
					tbsRoleMenuBean.setMenuIdFun(roleAuthTree[i]);
					map.put("id", roleAuthTree[i]);
					TbsMenuBean tbsMenuBean=tbsMenuService.selectByMap(map).get(0);
					if(tbsMenuBean.getIsMenu()!=0){
						map.put("id", tbsMenuBean.getParentId());
						tbsMenuBean=tbsMenuService.selectByMap(map).get(0);
						tbsRoleMenuBean.setMenuId(tbsMenuBean.getId());
					}else{
						tbsRoleMenuBean.setMenuId(roleAuthTree[i]);
					}
					tbsRoleMenuService.insert(tbsRoleMenuBean);
				}
			}
			tbsRoleBean.setId(roleId);// 设置主键
			tbsRoleService.insert(tbsRoleBean);// 入库
			util.toJsonMsg(response, 0, null);
			return;
		} catch (Exception e) {
			util.toJsonMsg(response, 1, null);
			e.printStackTrace();
		}
		util.toJsonMsg(response, 1, null);
	}

	/**
	 * 
	 * <br>
	 * <b>功能：</b>保存 TbsRoleBean信息<br>
	 * <b>作者：</b>wolf<br>
	 * <b>日期：</b> 2012-10-11 <br>
	 * 
	 * @return
	 */
	@RequestMapping("/save.html")
	public void save(HttpServletResponse response,TbsRoleBean tbsRoleBean,String... roleAuthTree) {
		if (tbsRoleBean.getId() != null) {
			TbsRoleMenuBean tbsRoleMenuBean = new TbsRoleMenuBean();
			tbsRoleMenuBean.setRoleId(tbsRoleBean.getId());
			try {
				tbsRoleMenuService.deleteByEntity(tbsRoleMenuBean);
				if (null != roleAuthTree) {
					Map<String,Object> map=new HashMap<String, Object>();
					for (int i = 0; i < roleAuthTree.length; i++) {
						tbsRoleMenuBean.setId(util.getUidString());
						tbsRoleMenuBean.setRoleId(tbsRoleBean.getId());
						tbsRoleMenuBean.setMenuIdFun(roleAuthTree[i]);
						map.put("id", roleAuthTree[i]);
						TbsMenuBean tbsMenuBean=tbsMenuService.selectByMap(map).get(0);
						if(tbsMenuBean.getIsMenu()!=0){
							map.put("id", tbsMenuBean.getParentId());
							tbsMenuBean=tbsMenuService.selectByMap(map).get(0);
							tbsRoleMenuBean.setMenuId(tbsMenuBean.getId());
						}else{
							tbsRoleMenuBean.setMenuId(roleAuthTree[i]);
						}
						tbsRoleMenuService.insert(tbsRoleMenuBean);
					}
				}
				tbsRoleService.updateByPrimaryKey(tbsRoleBean);
				util.toJsonMsg(response, 0, null);
				return;
			} catch (Exception e) {
				util.toJsonMsg(response, 1, null);
				e.printStackTrace();
			}
		}
		util.toJsonMsg(response, 1, null);
	}

	/**
	 * 
	 * <br>
	 * <b>功能：</b>删除${codeName}信息<br>
	 * <b>作者：</b>罗泽军<br>
	 * <b>日期：</b> Dec 8, 2011 <br>
	 * 
	 * @return
	 */
	@RequestMapping("/del.html")
	public void del(String[] ids, HttpServletResponse response) {
		System.out.println("del-ids:" + Arrays.toString(ids));
		try {
			if (ids != null) {
				tbsRoleService.deleteByPrimaryKeys(ids);
				TbsRoleMenuBean trmb = new TbsRoleMenuBean();
				TbsRoleUserBean trub = new TbsRoleUserBean();
				for (int i = 0; i < ids.length; i++) {
					trmb.setRoleId(ids[i]);
					tbsRoleMenuService.deleteByEntity(trmb);
					trub.setRoleId(ids[i]);
					tbsRoleUserService.deleteByEntity(trub);
				}
				util.toJsonMsg(response, 0, null);
				return;
			}
			util.toJsonMsg(response, 1, null);
		} catch (Exception e) {
			util.toJsonMsg(response, 1, null);
			log.error(e);
		}
	}
}