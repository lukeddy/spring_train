package com.server.controller;

import com.server.service.MyService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 主面板控制器
 */
@Controller
public class HomeController extends BaseController {

    public static final Logger logger = org.slf4j.LoggerFactory.getLogger(HomeController.class);

    @Autowired
    public MyService myService;

    @RequestMapping(value="/")
    public String index(){
        myService.hello();
        return "welcome";
    }

}
