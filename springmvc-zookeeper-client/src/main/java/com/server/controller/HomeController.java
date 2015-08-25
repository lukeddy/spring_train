package com.server.controller;

import com.server.service.MyService;
import com.server.tools.CrudExample;
import com.server.tools.ZKManager;
import org.codehaus.jackson.map.util.JSONPObject;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

    @RequestMapping(value="/registerNode")
    public String register(){
        ZKManager.register("66.66.66.66");
        return "welcome";
    }

    @RequestMapping(value="/getServerList")
    @ResponseBody
    public List<String> getServerList(){


        return null;
    }

}

