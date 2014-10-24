package bean.model;

public class TbsMenuModel extends BaseModel {

    private String id; //唯一
    private String parentId; //父节点
    private String name; //名称
    private Integer isMenu; //菜单类型
    private Integer type; //系统类型
    private Integer sortNumber; //排序
    private String url; //地址
    private Integer status; //状态
    private java.sql.Timestamp createTime; //时间
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>唯一<br>
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
	 * <b>功能：</b>唯一<br>
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
	 * <b>功能：</b>父节点<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return parentId
	 */
	public String getParentId(){
	   return parentId;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>父节点<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param parentId
	 */
	public void setParentId(String parentId){
	   this.parentId=parentId;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>名称<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return name
	 */
	public String getName(){
	   return name;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>名称<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param name
	 */
	public void setName(String name){
	   this.name=name;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>菜单类型<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return isMenu
	 */
	public Integer getIsMenu(){
	   return isMenu;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>菜单类型<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param isMenu
	 */
	public void setIsMenu(Integer isMenu){
	   this.isMenu=isMenu;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>系统类型<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return type
	 */
	public Integer getType(){
	   return type;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>系统类型<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param type
	 */
	public void setType(Integer type){
	   this.type=type;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>排序<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return sortNumber
	 */
	public Integer getSortNumber(){
	   return sortNumber;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>排序<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param sortNumber
	 */
	public void setSortNumber(Integer sortNumber){
	   this.sortNumber=sortNumber;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>地址<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return url
	 */
	public String getUrl(){
	   return url;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>地址<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param url
	 */
	public void setUrl(String url){
	   this.url=url;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>状态<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return status
	 */
	public Integer getStatus(){
	   return status;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>状态<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param status
	 */
	public void setStatus(Integer status){
	   this.status=status;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>时间<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return createTime
	 */
	public java.sql.Timestamp getCreateTime(){
	   return createTime;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>时间<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param createTime
	 */
	public void setCreateTime(java.sql.Timestamp createTime){
	   this.createTime=createTime;
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
	   return "id:"+id+"|parentId:"+parentId+"|name:"+name+"|isMenu:"+isMenu+"|type:"+type+"|sortNumber:"+sortNumber+"|url:"+url+"|status:"+status+"|createTime:"+createTime;
    }
	
}
