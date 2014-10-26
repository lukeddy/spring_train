package util.json;
import java.awt.List;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
public class Main {
	public static void main(String[] args) throws JSONException{
		Buy buy = new Buy() ;
		buy.setAmount("1") ;
		buy.setBuy_id("2") ;
		buy.setBuy_time("3") ;
		buy.setDiscount_id("4") ;
		ArrayList list = new ArrayList() ;
		Person person = new Person() ;
		person.setAddress("address") ;
		person.setBirthday("birthday") ;
		list.add(person) ;
		buy.setList(list) ;
		System.out.println("var jsonStr = " + JSONUtil.toJSONString(buy)+";");
		
		//System.out.println("adfads");
		JSONObject js=new JSONObject();
		
	}
}
