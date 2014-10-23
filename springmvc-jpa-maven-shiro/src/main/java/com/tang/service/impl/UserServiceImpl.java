package com.tang.service.impl;

import javax.annotation.Resource;

import com.tang.entity.User;
import com.tang.service.IBaseService;
import com.tang.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl extends DefultBaseService implements IUserService {

	@Resource(name="baseService")
    private IBaseService baseService;
	
	public User getByAccount(String account){
		User user = null;
		try {
			user = (User) baseService.getUniqueResultByJpql("from User as o where o.account=?", account);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

    public boolean register(User user){
		boolean flag = false;
		try {
			baseService.save(user);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

    @Override
    public boolean isUserExist(String account) {
        return getByAccount(account)!=null;
    }

    @Override
    public void insertTest() throws Exception {
        throw new Exception("insert data error");
    }

}
