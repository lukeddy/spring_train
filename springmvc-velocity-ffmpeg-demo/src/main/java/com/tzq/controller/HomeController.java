package com.tzq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-6-9
 * Time: 下午2:24
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class HomeController {

    @RequestMapping(value ="/index" )
    public  String index(){
        return "index";
    }
}
