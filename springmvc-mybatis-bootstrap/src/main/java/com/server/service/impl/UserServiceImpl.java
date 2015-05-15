package com.server.service.impl;

import com.server.dao.BaseDao;
import com.server.model.User;
import com.server.service.UserService;
import com.server.util.MD5Encrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统用户服务类
 */
@Service
public class UserServiceImpl implements UserService {

    public static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private BaseDao baseDao;

    @Override
    public List<User> findAll() {
        return baseDao.getList("userMapper.findAll",null);
    }

    @Override
    public User findUser(String username, String pwd) {
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("username",username);
        params.put("pwd", MD5Encrypt.encrypt(pwd));
        return baseDao.get("userMapper.isUserExist",params);
    }

    @Override
    public boolean isUserNameExist(String username) {
        List<User> userList =baseDao.getList("userMapper.findUserByUserName",username);
        if(null!=userList&&userList.size()>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean isUserExist(String username, String pwd) {
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("username",username);
        params.put("pwd", MD5Encrypt.encrypt(pwd));
        User adminUser=baseDao.get("userMapper.isUserExist",params);
        if(null!=adminUser){
            return true;
        }
        return false;
    }

    @Override
    public void addUser(User adminUser) {
        String encryptedPwd=MD5Encrypt.encrypt(adminUser.getPwd());
        adminUser.setPwd(encryptedPwd);
        baseDao.insert("userMapper.insert",adminUser);
    }

    /**
     * 修改密码
     * @param newPwd
     * @param id
     */
    @Override
    public void changePwd(String newPwd, int id) {
       Map<String,Object> params=new HashMap<String,Object>();
       params.put("pwd",MD5Encrypt.encrypt(newPwd));
       params.put("id",id);

       baseDao.update("userMapper.updatePwd",params);
    }

    /**
     * 用户锁定，解锁
     * @param lockState
     * @param id
     */
    @Override
    public void changeLockState(int lockState, int id) {
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("lockState",lockState);
        params.put("id",id);

        baseDao.update("userMapper.updateLockState",params);
    }

    @Override
    public void updateLastLoginTime(int id) {
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("lastLoginTime",System.currentTimeMillis());
        params.put("id",id);

        baseDao.update("userMapper.updateLastLoginTime",params);
    }

}
