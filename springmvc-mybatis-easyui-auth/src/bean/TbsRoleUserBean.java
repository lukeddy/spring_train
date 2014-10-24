package bean;
public class TbsRoleUserBean extends BaseBean {

	private String id; //主键
	private String userId; //用户
	private String roleId; //角色
	
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
	 * <b>功能：</b>用户<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return userId
	 */
	public String getUserId(){
	   return userId;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>用户<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param userId
	 */
	public void setUserId(String userId){
	   this.userId=userId;
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
	 * <b>功能：</b>重写<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return
	 */
    public String toString(){
	   return "id:"+id+"|userId:"+userId+"|roleId:"+roleId;
    }

    ///////////////////////////增加/////////////////////////

}
