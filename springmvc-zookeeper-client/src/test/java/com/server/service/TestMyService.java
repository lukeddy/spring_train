package com.server.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2015/8/24.
 */
public class TestMyService extends TestBase {
    @Autowired
    private MyService myService;

    @Test
    public void testTest(){
        myService.hello();
    }

}
