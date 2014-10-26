package bean.model;

public class TbcConTempModel extends BaseModel {

    private String id; //id
    private String type; //type
    private String title; //title
    private String text; //text
    private String image; //image
	
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
	 * <b>功能：</b>title<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return title
	 */
	public String getTitle(){
	   return title;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>title<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param title
	 */
	public void setTitle(String title){
	   this.title=title;
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
	 * <b>功能：</b>image<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return image
	 */
	public String getImage(){
	   return image;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>image<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param image
	 */
	public void setImage(String image){
	   this.image=image;
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
	   return "id:"+id+"|type:"+type+"|title:"+title+"|text:"+text+"|image:"+image;
    }
	
}
