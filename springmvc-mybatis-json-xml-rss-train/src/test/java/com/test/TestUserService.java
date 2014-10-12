package com.test;

import com.alibaba.fastjson.JSON;
import com.test.base.TestServiceBase;
import junit.framework.Assert;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tangzq.model.User;
import tangzq.service.UserServiceI;

import java.util.Date;
import java.util.List;

/**
 * UserService测试类
 */

public class TestUserService extends TestServiceBase {

	private static final Logger logger = Logger.getLogger(TestUserService.class);

    @Autowired
    private UserServiceI userService;


    @Test
    public void testInsert(){
        User u=new User();
        u.setCreatedatetime(new Date());
        u.setUpdateTime(new Date());
        u.setName("zhangsan");
        u.setPwd("hello");
        u.setModifydatetime(new Date());
        u.setCreateTime(new Date());
        int rowNum=  userService.insert(u);
        Assert.assertNotNull(rowNum);
    }

    @Test
    public void testUpdate(){
        User u=new User();
        u.setId("1");
        u.setCreatedatetime(new Date());
        u.setUpdateTime(new Date());
        u.setName("wangwu");
        u.setPwd("666666");
        u.setModifydatetime(new Date());
        u.setCreateTime(new Date());
        int rowNum=  userService.updateUser(u);
        Assert.assertNotNull(rowNum);
    }



    @Test
	public void test1() {
		User u = userService.getUserById("1");
		logger.info(JSON.toJSONStringWithDateFormat(u, "yyyy-MM-dd HH:mm:ss"));
	}

	@Test
	public void test2() {
		List<User> l = userService.getAll();
		logger.info(JSON.toJSONStringWithDateFormat(l, "yyyy-MM-dd HH:mm:ss"));
	}

	@Test
	public void test3() {
		List<User> l = userService.getAll2();
		logger.info(JSON.toJSONStringWithDateFormat(l, "yyyy-MM-dd HH:mm:ss"));
	}

	@Test
	public void test4() {
		List<User> l = userService.getAll3();
		logger.info(JSON.toJSONStringWithDateFormat(l, "yyyy-MM-dd HH:mm:ss"));
	}
}
