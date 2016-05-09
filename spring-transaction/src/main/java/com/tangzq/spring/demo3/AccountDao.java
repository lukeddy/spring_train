package com.tangzq.spring.demo3;

public interface AccountDao {

	public void outMoney(String out,Double money);
	
	public void inMoney(String in,Double money);
}
