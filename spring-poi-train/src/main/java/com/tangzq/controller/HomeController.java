package com.tangzq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页控制器
 */
@Controller
public class HomeController {

    /**
     * 跳转到首页
     * @return
     */
    @RequestMapping(value="/index")
    public String index(){
        return "index";
    }
}
