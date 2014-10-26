package bean.model;

public class TbsUserModel extends BaseModel {

    private String id; //主键
    private String username; //用户名
    private String password; //密码
    private java.sql.Timestamp time; //登录时间
    private String ip; //ip
    private Integer count; //次数
    private Integer isLock; //锁定
    private java.sql.Timestamp lockTime; //锁定时间
    private Integer failCount; //失败次数
    private Integer isAdmin; //管理员
	
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
	 * <b>功能：</b>用户名<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return username
	 */
	public String getUsername(){
	   return username;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>用户名<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param username
	 */
	public void setUsername(String username){
	   this.username=username;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>密码<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return password
	 */
	public String getPassword(){
	   return password;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>密码<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param password
	 */
	public void setPassword(String password){
	   this.password=password;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>登录时间<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return time
	 */
	public java.sql.Timestamp getTime(){
	   return time;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>登录时间<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param time
	 */
	public void setTime(java.sql.Timestamp time){
	   this.time=time;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>ip<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return ip
	 */
	public String getIp(){
	   return ip;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>ip<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param ip
	 */
	public void setIp(String ip){
	   this.ip=ip;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>次数<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return count
	 */
	public Integer getCount(){
	   return count;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>次数<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param count
	 */
	public void setCount(Integer count){
	   this.count=count;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>锁定<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return isLock
	 */
	public Integer getIsLock(){
	   return isLock;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>锁定<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param isLock
	 */
	public void setIsLock(Integer isLock){
	   this.isLock=isLock;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>锁定时间<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return lockTime
	 */
	public java.sql.Timestamp getLockTime(){
	   return lockTime;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>锁定时间<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param lockTime
	 */
	public void setLockTime(java.sql.Timestamp lockTime){
	   this.lockTime=lockTime;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>失败次数<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return failCount
	 */
	public Integer getFailCount(){
	   return failCount;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>失败次数<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param failCount
	 */
	public void setFailCount(Integer failCount){
	   this.failCount=failCount;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>管理员<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return isAdmin
	 */
	public Integer getIsAdmin(){
	   return isAdmin;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>管理员<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param isAdmin
	 */
	public void setIsAdmin(Integer isAdmin){
	   this.isAdmin=isAdmin;
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
	   return "id:"+id+"|username:"+username+"|password:"+password+"|time:"+time+"|ip:"+ip+"|count:"+count+"|isLock:"+isLock+"|lockTime:"+lockTime+"|failCount:"+failCount+"|isAdmin:"+isAdmin;
    }
	
}
