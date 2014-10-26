package controller.admin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bean.TbsMenuBean;
import bean.TbsUserBean;

import service.TbsMenuService;
import service.TbsUserService;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import util.core.MethodUtil;
import util.spring.SessionUtil;

import bean.TbsMenuBean;
import service.TbsMenuService;

/**
 * <br>
 * <b>功能：</b>类功能描述<br>
 * <b>作者：</b>Wolf<br>
 * <b>日期：</b> 2012-12-27 <br>
 * <b>版权所有：<b>版权所有(C) 2012 QQ 405645010<br>
 * <b>更新者：</b><br>
 * <b>日期：</b> <br>
 * <b>更新内容：</b><br>
 */
@Controller
@RequestMapping("/admin")
public class SystemIndexControllerAdmin extends BaseController {
	private final static Logger log = Logger.getLogger(SystemIndexControllerAdmin.class);
	public static MethodUtil util = new MethodUtil();
	private StringBuffer sb = new StringBuffer();

	@Autowired
	private TbsUserService<TbsUserBean> tbsUserService;
	@Autowired
	private TbsMenuService<TbsMenuBean> tbsMenuService;

	/**
	 * 
	 * <br>
	 * <b>功能：</b>登录页面<br>
	 * <b>作者：</b>wolf<br>
	 * <b>日期：</b> 2012-10-25 <br>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public String from() {
		return "/admin/login";
	}

	/**
	 * 
	 * <br>
	 * <b>功能：</b>登录递交页<br>
	 * <b>作者：</b>wolf<br>
	 * <b>日期：</b> 2012-10-25 <br>
	 * 
	 * @param tbsUserBean
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login.html", method = RequestMethod.POST)
	public void submit(TbsUserBean tbsUserBean, HttpServletResponse response, HttpServletRequest request) throws Exception {
		String sessionVerifyCode = (String) SessionUtil.getAttr(request, "VERIFY_TYPE_COMMENT");// session验证码
		SessionUtil.removeAttr(request, "VERIFY_TYPE_COMMENT");
		String verifyCode = request.getParameter("verifyCode"); // 递交的验证码
		if (null == sessionVerifyCode || null == verifyCode || verifyCode.trim().length() != 4) {
			util.toJsonMsg(response, 2, "验证码长度有误或已失效");
			return;
		}
		if (!sessionVerifyCode.toUpperCase().equals(verifyCode.toUpperCase())) {
			util.toJsonMsg(response, 2, "验证码错误");
			return;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", tbsUserBean.getUsername());
		map.put("password", util.getDES("desKey!@#", tbsUserBean.getPassword(), 0));
		List<TbsUserBean> ltub = tbsUserService.selectByMap(map);
		if (null == ltub || ltub.size() != 1) {
			util.toJsonMsg(response, 1, "用户名密码有误");
			return;
		}
		tbsUserBean = ltub.get(0);
		Integer isAdmin = tbsUserBean.getIsAdmin() == null ? 1 : tbsUserBean.getIsAdmin();
		SessionUtil.setAttr(request, "isAdmin", "" + isAdmin);
		SessionUtil.setAttr(request, "tbsUserBean", tbsUserBean);
		List<String> authUrls = new ArrayList<String>();
		authUrls.add("/admin/index.html");
		SessionUtil.setAttr(request, "authUrls", authUrls);
		util.toJsonMsg(response, 0, null);
		return;
	}

	/**
	 * 
	 * <br>
	 * <b>功能：</b>主页<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-5-10 <br>
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/index.html")
	public String index(HttpServletRequest request, ModelMap modelMap) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("andCondition", "parentId IS NULL");
		map.put("orderCondition", "sortNumber");
		List<TbsMenuBean> parentMenu = tbsMenuService.selectByMap(map);
		String isAdmin = (String) SessionUtil.getAttr(request, "isAdmin");
		if (null != isAdmin && isAdmin.equals("0")) { // 管理员
			for (int i = 0; i < parentMenu.size(); i++) {
				String id = parentMenu.get(i).getId();
				map.clear();
				map.put("parentId", id);
				List<TbsMenuBean> child = tbsMenuService.selectByMap(map);
				for (int j = 0; j < child.size(); j++) {
					parentMenu.get(i).getChilds().add(child.get(j));
				}
			}
			modelMap.put("menus", parentMenu);
			return "admin/index";
		}
		// 其他用户
		TbsUserBean tbsUserBean = (TbsUserBean) SessionUtil.getAttr(request, "tbsUserBean");
		@SuppressWarnings("unchecked")
		List<String> authUrls = (List<String>) SessionUtil.getAttr(request, "authUrls");
		map.clear();
		map.put("cloumn", "menuIdFun");
		map.put("userId", tbsUserBean.getId());
		List<Map<String, Object>> childMenu = tbsUserService.selectByRoleUrls(map);
		if (childMenu != null && childMenu.size() > 0) { // 添加授权地址
			for (int i = 0; i < childMenu.size(); i++) {
				String roleUrls = (String) childMenu.get(i).get("url");
				String[] urls = roleUrls.split("\\,");
				for (int j = 0; j < urls.length; j++) {
					System.out.println("addUrl:" + urls[j]);
					authUrls.add(urls[j]);
				}
			}
		}
		map.clear();
		map.put("cloumn", "menuId");
		map.put("userId", tbsUserBean.getId());
		childMenu = tbsUserService.selectByRoleUrls(map);
		for (int i = 0; i < parentMenu.size(); i++) { // 主菜单找子菜单
			TbsMenuBean tbsMenuBean = parentMenu.get(i);
			if (null != childMenu && childMenu.size() > 0) {
				for (int j = 0; j < childMenu.size(); j++) {
					Map<String, Object> childMap = childMenu.get(j);
					System.out.println("childMap:" + childMap);
					String parentId = (String) childMap.get("parentId");
					if (tbsMenuBean != null && tbsMenuBean.getId().equals(parentId)) {
						String url = (String) childMap.get("url");
						TbsMenuBean bean = new TbsMenuBean();
						bean.setName((String) childMap.get("name"));
						bean.setUrl(url); // 页面显示URL
						authUrls.add(url); // 权限URL
						bean.setParentId(parentId);
						parentMenu.get(i).getChilds().add(bean);
						System.out.println("childMap:" + childMap);
					}
				}
			}
			if (tbsMenuBean.getChilds().size() == 0) { // 没找到子菜单 删除自己
				parentMenu.remove(i);
				i--;
			}
		}
		SessionUtil.setAttr(request, "authUrls", authUrls);// 重置
		modelMap.put("menus", parentMenu);
		return "admin/index";
	}

	/**
	 * 
	 * <br>
	 * <b>功能：</b>退出<br>
	 * <b>作者：</b>wolf<br>
	 * <b>日期：</b> 2013-1-21 <br>
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/exit.html")
	public String exit(HttpSession session) {
		SessionUtil.removeSessionAll(session);
		return "/admin/login";
	}

	/**
	 * 
	 * <br>
	 * <b>功能：</b>同步数目录递归JSON<br>
	 * <b>作者：</b>wolf<br>
	 * <b>日期：</b> 2012-12-27 <br>
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/dirJson.html")
	@ResponseBody
	public synchronized String dirJson(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		String path = request.getSession().getServletContext().getRealPath("/");
		System.out.println("RealPath:" + path);
		if (sb.length() > 0)
			sb.delete(0, sb.length());// 清除缓存
		String commonPathJson = this.getJsonData(new File(path)); // 项目路径Json
		commonPathJson = "[" + commonPathJson + "]";
		System.out.println("dirJson:" + commonPathJson);
		return commonPathJson;
	}

	/**
	 * 
	 * <br>
	 * <b>功能：</b>异步树 递归<br>
	 * <b>作者：</b>wolf<br>
	 * <b>日期：</b> 2013-1-15 <br>
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/asyJson.html")
	@ResponseBody
	public synchronized String asyJson(String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String asyJson = null;
		String path = null;

		if (null == id) {
			path = request.getSession().getServletContext().getRealPath("/");
		} else {
			path = new String(new BASE64Decoder().decodeBuffer(URLDecoder.decode(id, "utf-8")));
		}
		System.out.println("id:" + id + "|asyJsonPath:" + path);
		if (sb.length() > 0)
			sb.delete(0, sb.length());// 清除缓存
		asyJson = "[" + this.getAsyJsonData(new File(path)) + "]";
		System.out.println(asyJson);
		return asyJson;
	}

	/**
	 * 
	 * <br>
	 * <b>功能：</b>打开文件<br>
	 * <b>作者：</b>wolf<br>
	 * <b>日期：</b> 2012-9-5 <br>
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/openFile.html")
	public String getTreeOpenFile(String path, String id, ModelMap modelMap) throws IOException {
		System.out.println("path:" + path);
		modelMap.addAttribute("path", path); // base64加密路径
		path = new String(new BASE64Decoder().decodeBuffer(URLDecoder.decode(id, "utf-8")));
		File file = new File(path);
		if (!file.exists()) {
			return "error";
		}
		System.out.println("path:" + path);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
		String line = null;
		StringBuffer sb = new StringBuffer();
		while ((line = br.readLine()) != null) {
			sb.append(line).append("\r");
		}
		String text = sb.toString();
		text = text.replaceAll("<", "&lt");
		modelMap.addAttribute("str", text);
		// modelMap.addAttribute("id", id);
		modelMap.addAttribute("id", util.getMD5(id, null, 1)); // utils.getMD5UTF8(filePath)
		// modelMap.addAttribute("")
		// System.out.println("modelMap:"+modelMap);
		return "admin/SystemTree/openFile";
	}

	/**
	 * 
	 * <br>
	 * <b>功能：</b>保存文件<br>
	 * <b>作者：</b>wolf<br>
	 * <b>日期：</b> 2012-9-10 <br>
	 * 
	 * @param request
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/saveFile.html")
	@ResponseBody
	// 输出字符串
	public String getTreeSave(String path, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
		String result = "n";
		if (null == path)
			return result; // 路径为空
		path = new String(new BASE64Decoder().decodeBuffer(URLDecoder.decode(path, "utf-8")));
		String textarea = request.getParameter("textarea") == null ? "" : request.getParameter("textarea"); // 写入内容
		textarea = textarea.trim();
		System.out.println("savePath:" + path);
		// System.out.println("textarea:" + textarea);
		File file = new File(path);
		if (!file.exists()) {
			return result;
		}
		file.delete(); // 存在删除文件
		try {
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file), "utf-8");
			outputStreamWriter.write(textarea);
			outputStreamWriter.flush();
			outputStreamWriter.close();
			return "y";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * <br>
	 * <b>功能：</b>文件树JSON<br>
	 * <b>作者：</b>wolf<br>
	 * <b>日期：</b> 2012-9-12 <br>
	 * 
	 * @param file
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private synchronized String getJsonData(File file) throws UnsupportedEncodingException {
		if (!file.exists()) {
			return null;
		}
		boolean isFile = file.isFile();
		boolean isDir = file.isDirectory();
		String type = "file";
		if (isDir)
			type = "folder";

		String fileName = file.getName();
		String filePath = file.getPath();
		String md5Str = util.getMD5(filePath, null, 1);
		String base64Encoder = URLEncoder.encode(new BASE64Encoder().encode(filePath.getBytes()), "UTF-8");
		String url = "/admin/openFile.html"; // 路径
		// System.out.println(base64Encoder);
		sb.append("{"); // ,\"attributes\":{\"url\":\"/admin/tree/openFile.html\",\"target\":\"mainFrame\"
		sb.append("\"id\":\"" + md5Str + "\",\"text\":\"" + fileName + "\"");
		sb.append(",\"attributes\":{\"text\":\"" + fileName + "\",\"url\":\"" + url + "\",\"type\":\"" + type + "\",\"path\":\"" + base64Encoder + "\"}");
		if (isDir) {
			File fileList[] = file.listFiles(new FileFilter() {
				public boolean accept(File pathname) {
					String fileNameLower = pathname.getName().toLowerCase();
					if (pathname.isHidden())
						return false;
					/*********** 隐藏文件过滤 ***********/
					if (fileNameLower.matches(".*(meta-inf|templates)$|.*.(gif|jpg|png|ico|class|.jar)$")) {
						return false;
					}
					return true;
				}
			});
			// sb.append(",\"attributes\":{\"id\":\""+md5Str+"\",\"path\":\""+base64Encoder+"\"}");
			if (fileList.length > 0) {
				sb.append(",\"state\":\"closed\",\"children\":[");
				for (int i = 0; i < fileList.length; i++) {
					if (i > 0)
						sb.append(",");
					this.getJsonData(fileList[i]);
				}
				sb.append("]");
			}
		}
		// target="mainFrame"
		if (isFile) {
			// sb.append(",\"state\":\"closed\""); //\"target\":\"mainFrame\",
			// sb.append(",\"attributes\":{\"id\":\""+md5Str+"\",\"path\":\""+base64Encoder+"\"}");
		}
		sb.append("}");
		return sb.toString();
	}

	/**
	 * 
	 * <br>
	 * <b>功能：</b>异步数实现<br>
	 * <b>作者：</b>wolf<br>
	 * <b>日期：</b> 2013-1-15 <br>
	 * 
	 * @param file
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private synchronized String getAsyJsonData(File file) throws UnsupportedEncodingException {
		if (!file.exists()) {
			return null;
		}
		File fileList[] = file.listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				String fileNameLower = pathname.getName().toLowerCase();
				if (pathname.isHidden())
					return false;
				/*********** 隐藏文件过滤 ***********/
				if (fileNameLower.matches(".*(meta-inf|templates)$|.*.(gif|jpg|png|ico|class|.jar|.zip|.gz|.sql)$")) {
					return false;
				}
				return true;
			}
		});
		for (int i = 0; i < fileList.length; i++) {
			file = fileList[i];
			boolean isDir = file.isDirectory();
			String type = "file";
			String state = "open";
			if (isDir) {
				type = "folder";
				state = "closed";
			}
			String fileName = file.getName();
			String filePath = file.getPath();
			// String md5Str = utils.getMD5UTF8(filePath);
			String base64Encoder = URLEncoder.encode(new BASE64Encoder().encode(filePath.getBytes()), "UTF-8");
			String url = "/admin/openFile.html"; // 路径
			// System.out.println(base64Encoder);
			sb.append("{"); // ,\"attributes\":{\"url\":\"/admin/tree/openFile.html\",\"target\":\"mainFrame\"
			sb.append("\"id\":\"" + base64Encoder + "\",\"text\":\"" + fileName + "\",\"state\":\"" + state + "\"");
			sb.append(",\"attributes\":{\"text\":\"" + fileName + "\",\"url\":\"" + url + "\",\"type\":\"" + type + "\",\"path\":\"" + base64Encoder + "\"}");
			sb.append("},");
		}
		if (fileList.length > 0) {
			sb.delete(sb.length() - 1, sb.length());
		} else {
			sb.append("");
		}
		return sb.toString();
	}

	/**
	 * 
	 * <br>
	 * <b>功能：</b>修复用户<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-5-20 <br>
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/rollBack.html")
	public void backUpAdmin(HttpServletResponse response) {
		TbsUserBean tbsUserBean = new TbsUserBean();
		try {
			tbsUserBean.setUsername("admin");
			tbsUserService.deleteByEntity(tbsUserBean);
			tbsUserBean.setUsername("test");
			tbsUserService.deleteByEntity(tbsUserBean);
			tbsUserBean.setId(util.getUidString());
			tbsUserBean.setUsername("admin");
			tbsUserBean.setPassword("adb72b256f38df9e");
			tbsUserBean.setIsAdmin(0);
			tbsUserService.insert(tbsUserBean);
			tbsUserBean.setId(util.getUidString());
			tbsUserBean.setUsername("test");
			tbsUserBean.setPassword("adb72b256f38df9e");
			tbsUserBean.setIsAdmin(1);
			tbsUserService.insert(tbsUserBean);
			util.toJsonMsg(response, 0, null);
		} catch (Exception e) {
			e.printStackTrace();
			util.toJsonMsg(response, 1, null);
		}
	}
}
