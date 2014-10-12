package com.test.base;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import tangzq.controller.UserController;
import tangzq.service.UserServiceI;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:spring-servlet.xml", "classpath:spring-mybatis.xml"})
@WebAppConfiguration
public class TestControllerBase {

    @Autowired
    private WebApplicationContext wac;   //忽略错误提示“Could not autowire. No beans of 'WebApplicationContext' type found”

    @Autowired
    private UserController userController;//你要测试的Controller

    @Autowired
    protected UserServiceI userService;


    protected MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }
}
