package com.tangzq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    @RequestMapping(value="/home")
    public String index(
            ModelMap modelMap
    ){
        return "index";
    }
}
