package com.tangzq.service;

import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2015/1/22.
 */
@Service
public class HelloService {
    public String getMsg(){
        return "Hi,I am service message，很高兴认识大家!";
    }
}
