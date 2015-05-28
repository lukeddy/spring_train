package com.tangzq.controller;

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

/**
 * i18n国际化控制器
 */
@Controller
@RequestMapping(value="/i18n")
public class I18nController {

    /**
     * 跳转到i18n测试页面
     * @return
     */
    @RequestMapping(value="/")
    public String welcome(){
        return "i18n_test";
    }

    /**
     * 修改语言测试
     * @param model
     * @param request
     * @param response
     * @param session
     * @return
     */
    @RequestMapping(value="/changeLang")
    public String changeLanguage(Model model, HttpServletRequest request,HttpServletResponse response,HttpSession session){
        WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(session.getServletContext());
        Locale locale = RequestContextUtils.getLocaleResolver(request).resolveLocale(request);
        String testMsg = applicationContext.getMessage("test.msg",null,locale);
        System.out.println(testMsg);
        model.addAttribute("i18n_msg",testMsg);
        return "i18n_test";


    }
}