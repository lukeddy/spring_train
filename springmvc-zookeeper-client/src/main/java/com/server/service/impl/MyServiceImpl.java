package com.server.service.impl;

import com.server.service.MyService;
import org.springframework.stereotype.Service;

/**
 * Service测试类实现
 */
@Service
public class MyServiceImpl implements MyService {
    public void hello() {
        System.out.println("=======================I am method of service class=======================");
    }
}
