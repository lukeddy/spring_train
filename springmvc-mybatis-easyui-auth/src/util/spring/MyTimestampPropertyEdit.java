
package util.spring;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * <br>
 * <b>功能：</b>类功能描述<br>
 * <b>作者：</b>Wolf<br>
 * <b>日期：</b> 2012-6-19 <br>
 * <b>版权所有：<b>版权所有(C) 2012 QQ 405645010<br>
 * <b>更新者：</b><br>
 * <b>日期：</b> <br>
 * <b>更新内容：</b><br>
 */
public class MyTimestampPropertyEdit extends PropertyEditorSupport {
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if(text.length()==19){
			try {
				setValue(new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(text).getTime()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return;
	}
  
}