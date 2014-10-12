package com.tang.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 10/21/13
 * Time: 9:01 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class HomeController {
      @RequestMapping(value="/loginPage",method = RequestMethod.GET)
      public String loginPage(){
          return "login";
      }

      @RequestMapping(value="/loginSuccess",method = RequestMethod.GET)
      public String loginSuccess(){
        return "success";
      }

      @RequestMapping(value="/noAuthentication",method = RequestMethod.GET)
      public String hasNoPermission(){
        return "error/noAuthentication";
      }

    @RequestMapping(value="/logout",method = RequestMethod.GET)
    public String logout(){
        SecurityUtils.getSubject().logout();
        return "login";
    }
}
