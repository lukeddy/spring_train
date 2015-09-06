package com.tangzq.controller;

import com.tangzq.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页控制器
 */
@Controller
public class HomeController {

    @Autowired
    private MyService myService;

    /**
     * 跳转到首页
     * @return
     */
    @RequestMapping(value="/home")
    public String index(
            ModelMap modelMap
    ){
        modelMap.addAttribute("appName", myService.getAppName());
        return "index";
    }
}
