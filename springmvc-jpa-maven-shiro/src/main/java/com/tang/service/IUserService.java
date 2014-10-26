package com.tang.service;

import com.tang.entity.User;

public interface IUserService extends IBaseService{
	
	public User getByAccount(String account);

	public boolean register(User user);

    public boolean isUserExist(String account);
    public void insertTest() throws Exception;
}
