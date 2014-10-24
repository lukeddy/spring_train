package bean;
public class TbcTempBean extends BaseBean {

	private String id; //序列
	private String href; //地址
	private String text; //名称
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>序列<br>
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
	 * <b>功能：</b>序列<br>
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
	 * <b>功能：</b>地址<br>
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
	 * <b>功能：</b>地址<br>
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
	 * <b>功能：</b>名称<br>
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
	 * <b>功能：</b>名称<br>
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
	   return "id:"+id+"|href:"+href+"|text:"+text;
    }

    ///////////////////////////增加/////////////////////////

}
