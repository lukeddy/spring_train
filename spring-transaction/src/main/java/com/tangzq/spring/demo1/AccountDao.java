package com.tangzq.spring.demo1;

public interface AccountDao {

	public void outMoney(String out,Double money);
	
	public void inMoney(String in,Double money);
}
