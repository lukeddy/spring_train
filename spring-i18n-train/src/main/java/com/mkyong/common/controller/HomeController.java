package com.mkyong.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

@Controller
public class HomeController{

    @RequestMapping(value="/")
    public String welcome(){
        return "TestPage";
    }

    @RequestMapping(value="changeLang")
    public String changeLanguage(Model model, HttpServletRequest request,HttpServletResponse response,HttpSession session){
        WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(session.getServletContext());
        Locale locale = RequestContextUtils.getLocaleResolver(request).resolveLocale(request);
        String testMsg = applicationContext.getMessage("test.msg",null,locale);
        System.out.println(testMsg);
        model.addAttribute("i18n_msg",testMsg);
        return "TestPage";


    }
}