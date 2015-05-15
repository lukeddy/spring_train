package com.server.service;


import com.server.model.User;

import java.util.List;

public interface UserService {

    public List<User> findAll();
    public User findUser(String username,String pwd);
    public boolean isUserNameExist(String username);
    public boolean isUserExist(String username,String pwd);
    public void addUser(User user);
    public void changePwd(String newPwd,int id);
    public void changeLockState(int lockState,int id);
    public void updateLastLoginTime(int id);

}
