
package util.spring;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * <br>
 * <b>功能：</b>加载配置文件<br>
 * <b>作者：</b>Wolf<br>
 * <b>日期：</b> 2012-6-14 <br>
 * <b>版权所有：<b>版权所有(C) 2012 QQ 405645010<br>
 * <b>更新者：</b><br>
 * <b>日期：</b> <br>
 * <b>更新内容：</b><br>
 */
public class LoadResource {
       private static Properties properties=new Properties();
       private static String config="/dataSourceConfig.properties"; 
       static{
    	     InputStream is=LoadResource.class.getClass().getResourceAsStream(config);
    	     try {
				properties.load(is);
				is.close();
				System.setProperties(properties);
				for(Map.Entry<Object, Object> entity:System.getProperties().entrySet()){
					System.out.println(entity.getKey()+":"+entity.getValue());
				}
				System.out.println(System.getProperty("jdbc.master.url"));
			} catch (IOException e) {
				e.printStackTrace();
			}
       }
       public LoadResource(){
    	   System.out.println("hello");
    	   InputStream is=LoadResource.class.getClass().getResourceAsStream(config);
       }
       public static void main(String[] args) {
    	   LoadResource sb=new LoadResource();
	   }
}

