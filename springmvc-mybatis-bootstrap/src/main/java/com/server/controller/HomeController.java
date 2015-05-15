package com.server.controller;

import com.server.model.User;
import com.server.service.UserService;
import com.server.util.ValidateCode;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 主面板控制器
 */
@Controller
public class HomeController extends BaseController {

    public static final Logger logger = org.slf4j.LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private UserService userService;


    @RequestMapping(value="/index")
    public String index(){
        return "redirect:/login";
    }

    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping(value="/login",method = RequestMethod.GET)
    public String pageLogin(){
        return "login";
    }

    /**
     * 登录
     * @param username
     * @param pwd
     * @param validateCode
     * @param model
     * @return
     */
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public String doLogin(
            @RequestParam(value = "username",required = true)String username,
            @RequestParam(value="pwd",required = true)String pwd,
            @RequestParam(value="validateCode",required = true)String validateCode,
            ModelMap model,
            HttpSession session){
        String code = (String) session.getAttribute("validateCode");
        String submitCode = validateCode;
        if (StringUtils.isEmpty(submitCode) || !StringUtils.equals(code,submitCode.toLowerCase())) {
            model.addAttribute("fail_msg", "验证码错误！");
            return  "login";
        }
        if(StringUtils.isEmpty(username)||StringUtils.isEmpty(pwd)){
            model.addAttribute("fail_msg", "用户名、密码不能为空！");
            return  "login";
        }else  if(!userService.isUserExist(username,pwd)){
            model.addAttribute("fail_msg", "用户名或者密码错误！");
            return  "login";
        }
        User u= userService.findUser(username,pwd);
        if(u.getLockState()==1){
            model.addAttribute("fail_msg", "该帐号已被锁定，请联系管理员！");
            return  "login";
        }

        session.setAttribute("user", u);
        userService.updateLastLoginTime(u.getId());
        return  "redirect:/sys/userManagement";
    }

    /**
     * 生成验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/validateCode")
    public void validateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-cache");
        String verifyCode = ValidateCode.generateTextCode(ValidateCode.TYPE_NUM_ONLY, 4, null);
        request.getSession().setAttribute("validateCode", verifyCode);
        response.setContentType("image/jpeg");
        BufferedImage bim = ValidateCode.generateImageCode(verifyCode, 90, 30, 3, true, Color.WHITE, Color.BLACK, null);
        ImageIO.write(bim, "JPEG", response.getOutputStream());
    }

    /**
     * 跳转到申请进行图片审查页面
     */
    @RequestMapping(value = "/imgcheck")
    public String imageCheckPage(){
        return "imgcheck_index";
    }



    /**
     * 登出系统
     * @param session
     * @return
     */
    @RequestMapping(value="/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        session.invalidate();
        return "redirect:/login";
    }

}
