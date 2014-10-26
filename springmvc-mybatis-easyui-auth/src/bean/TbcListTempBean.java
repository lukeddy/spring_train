package bean;
public class TbcListTempBean extends BaseBean {

	private String id; //id
	private String href; //href
	private String type; //type
	private String text; //text
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>id<br>
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
	 * <b>功能：</b>id<br>
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
	 * <b>功能：</b>href<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return href
	 */
	public String getHref(){
	   return href;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>href<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param href
	 */
	public void setHref(String href){
	   this.href=href;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>type<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return type
	 */
	public String getType(){
	   return type;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>type<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param type
	 */
	public void setType(String type){
	   this.type=type;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>text<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return text
	 */
	public String getText(){
	   return text;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>text<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param text
	 */
	public void setText(String text){
	   this.text=text;
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
	   return "id:"+id+"|href:"+href+"|type:"+type+"|text:"+text;
    }

    ///////////////////////////增加/////////////////////////

}
