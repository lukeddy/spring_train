package util.json;

import java.util.List;

public class Buy {
	private String buy_id ;
	private String user_id ;
	private String product_id ;
	private String discount_id ;
	private String amount ;
	private String money ;
	private String buy_time ;
	private String order_id ;
	@SuppressWarnings("unchecked")
	private List list ;
	
	
	@SuppressWarnings("unchecked")
	public List getList() {
		return list;
	}
	@SuppressWarnings("unchecked")
	public void setList(List list) {
		this.list = list;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getBuy_id() {
		return buy_id;
	}
	public void setBuy_id(String buy_id) {
		this.buy_id = buy_id;
	}
	public String getBuy_time() {
		return buy_time;
	}
	public void setBuy_time(String buy_time) {
		this.buy_time = buy_time;
	}
	public String getDiscount_id() {
		return discount_id;
	}
	public void setDiscount_id(String discount_id) {
		this.discount_id = discount_id;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
}
