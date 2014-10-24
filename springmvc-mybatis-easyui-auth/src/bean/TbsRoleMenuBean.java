package bean;
public class TbsRoleMenuBean extends BaseBean {

	private String id; //主键
	private String roleId; //角色
	private String menuIdFun; //功能
	private String menuId; //所属菜单
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>主键<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return id
	 */
	public String getId(){
	   return id;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>主键<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param id
	 */
	public void setId(String id){
	   this.id=id;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>角色<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return roleId
	 */
	public String getRoleId(){
	   return roleId;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>角色<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param roleId
	 */
	public void setRoleId(String roleId){
	   this.roleId=roleId;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>功能<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return menuIdFun
	 */
	public String getMenuIdFun(){
	   return menuIdFun;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>功能<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param menuIdFun
	 */
	public void setMenuIdFun(String menuIdFun){
	   this.menuIdFun=menuIdFun;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>所属菜单<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return menuId
	 */
	public String getMenuId(){
	   return menuId;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>所属菜单<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param menuId
	 */
	public void setMenuId(String menuId){
	   this.menuId=menuId;
	}
    
	/**
	 * 
	 * <br>
	 * <b>功能：</b>重写<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return
	 */
    public String toString(){
	   return "id:"+id+"|roleId:"+roleId+"|menuIdFun:"+menuIdFun+"|menuId:"+menuId;
    }

    ///////////////////////////增加/////////////////////////

}
