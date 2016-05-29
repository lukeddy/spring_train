package com.tangzq.service.impl;

import com.tangzq.service.AdditionService;
import org.apache.thrift.TException;

/**
 * 加法实现类
 */
public class AdditionServiceImpl implements AdditionService.Iface{
    public int add(int num1, int num2) throws TException {
        System.out.println(String.format("输入参数为：{}，{}",num1,num2));
        return num1+num2;
    }
}
