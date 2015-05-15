package com.server.service;

import com.server.model.User;
import com.server.util.MD5Encrypt;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 审查用户Service类测试
 */
public class TestUserSerivce extends TestBase {

    @Autowired
    private UserService userService;


    @Test
    public void testFindAll(){
        List<User> adminUserList= userService.findAll();
        Assert.assertNotNull(adminUserList);
        for(User au:adminUserList){
            System.out.println(au.getUsername()+","+au.getLastLoginTimeFormated());
        }
    }


    @Test
    public void testAddUser(){
        User user=new User();
        user.setUsername("abc123");
        user.setPwd(MD5Encrypt.encrypt("abc123"));
        user.setRole(1);

        userService.addUser(user);
    }

    @Test
    public void testInitPwd(){
        String superPwd=MD5Encrypt.encrypt("super123456");
        System.out.println("superPwd:"+superPwd);
        String testPwd=MD5Encrypt.encrypt("test123456");
        System.out.println("testPwd:"+testPwd);
    }

    @Test
    public void testIsUserExist(){
        boolean result= userService.isUserExist("test","test2015");
        Assert.assertTrue(result);
        result= userService.isUserExist("super","super2015");
        Assert.assertTrue(result);
    }

    @Test
    public void testIsUserNameExist(){
        Assert.assertTrue(userService.isUserNameExist("super"));
        Assert.assertTrue(!userService.isUserNameExist("trete"));
    }

    @Test
    public void testUpdateLastLoginTime(){
        userService.updateLastLoginTime(21);
    }
}
