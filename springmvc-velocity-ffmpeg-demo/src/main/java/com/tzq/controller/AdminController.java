package com.tzq.controller;

import com.tzq.domain.AjaxResponse;
import com.tzq.domain.FileList;
import com.tzq.domain.User;
import com.tzq.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController{
    Logger LOGGER = LoggerFactory.getLogger("AdminController");
    @Autowired
    @Qualifier("userService")
    private UserService userService;

    private User loginedUser;
    @RequestMapping(value = "/login")
    public String login(HttpSession session,@RequestParam("username") String username, @RequestParam("password") String password,RedirectAttributes redirectAttribute) {
        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            List user_list = userService.queryAll(" from User u where u.username='" + username + "' and u.password='" + password + "'");
            LOGGER.info("sessionId:"+session.getId());
            if (user_list.size() != 0) {
                loginedUser=(User)user_list.get(0);
                if(session.getServletContext().getAttribute("appUser")==null){
                    session.setAttribute("loginUser", loginedUser);
                    session.getServletContext().setAttribute("appUser", loginedUser);
                    session.getServletContext().setAttribute("loginedSessionId",session.getId());
                    return "redirect:/admin/home";
                }else{
                    redirectAttribute.addFlashAttribute("login_error","There's a user '"+((User)session.getServletContext().getAttribute("appUser")).getUsername()+"' already logined! <a style='font-weight:bolder;' href='/admin/logout'> Force it to logout.</a>");
                    session.getServletContext().removeAttribute("appUser");
                    session.getServletContext().removeAttribute("loginedSessionId");
                    session.removeAttribute("loginUser");
                    return  "redirect:/index";
                }
            } else {
                redirectAttribute.addFlashAttribute("login_error","Username or password error!");
                return "redirect:/index";
            }
        }

        return "index";
    }

    @RequestMapping(value = "/home")
    public String admin(HttpServletRequest request) {
        return "redirect:/admin/listfiles";
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        if(session!=null){
            //you must remove the key for single user logining
            session.getServletContext().removeAttribute("appUser");
            session.getServletContext().removeAttribute("loginedSessionId");
            session.removeAttribute("loginUser");
        }
        return "redirect:/index";
    }
    @RequestMapping("/goToChangePwd")
    public String goToChangePwdPage(){

        return "change_password";
    }
    @RequestMapping("/changepwd")
    public String changePassword(HttpSession session,String oldPwd,String newPwd,RedirectAttributes redirectAttribute){
         User user=(User)session.getAttribute("loginUser");
         List user_list = userService.queryAll(" from User u where u.username='" +user.getUsername() + "' and u.password='" + oldPwd + "'");
          if (user_list.size() != 0) {
                user.setPassword(newPwd);
                userService.editUpdate(user);
                session.setAttribute("loginUser",user);
                redirectAttribute.addFlashAttribute("changepwd_success","Successfully changed password!");
                return "redirect:/admin/goToChangePwd";
            } else {
                redirectAttribute.addFlashAttribute("changepwd_error","The old password error,please try again!");
                return "redirect:/admin/goToChangePwd";
            }

    }
    @RequestMapping(value="/listuser")
    public String listUser(){
        return "user_list";
    }

    @RequestMapping(value = "/userpage")
    public ModelAndView ajaxListFile(@RequestParam("page") Long page, @RequestParam("rows") int rows){
        FileList fileList = new FileList();

        Long count = userService.countAll(User.class);
        fileList.setRecords(count);
        fileList.setPage(page);
        Long total = 0l;
        if(count%rows==0){
            total = count/rows;
        }else {
            total = count/rows+1;
        }

        fileList.setTotal(total);
        List ft=userService.listAll(User.class,page.intValue(),rows);
        fileList.setRows(ft);
        ModelAndView mav = new ModelAndView("defaultJsonView");
        mav.addObject(fileList);
        return mav;
    }
    @RequestMapping("/addUser")
    public ModelAndView addUser(@RequestParam String username,@RequestParam String password){
        AjaxResponse ajaxResp=new AjaxResponse();
        ModelAndView mv=new ModelAndView("defaultJsonView");
        if(userService.isUserExist(username)){
            ajaxResp.setAjaxResult(AjaxResponse.FAIL);
            ajaxResp.setMessage("User '"+username+"' existed!");
            mv.addObject(ajaxResp);
            return mv;
        }else{
            User user=new User();
            user.setUsername(username);
            user.setPassword(password);
            userService.editSave(user);
            user=(User)userService.queryAll(" from User u where u.username='"+username+"'").get(0);
            ajaxResp.setAjaxResult(AjaxResponse.SUCCESS);
            ajaxResp.setRespObj(user);
            mv.addObject(ajaxResp);
            return  mv;
        }
    }
    @RequestMapping("/getUser")
    public ModelAndView getUser(@RequestParam("id") int id){
        AjaxResponse ajaxResp=new AjaxResponse();
        ModelAndView mv=new ModelAndView("defaultJsonView");
        List list=userService.queryAll(" from User u where u.id="+id);
        if(null!=list&&list.size()>0){
            ajaxResp.setAjaxResult(AjaxResponse.SUCCESS);
            ajaxResp.setRespObj((User) list.get(0));
            mv.addObject(ajaxResp);
            return  mv;
        }else{
            ajaxResp.setAjaxResult(AjaxResponse.FAIL);
            ajaxResp.setMessage("User does not exist!");
            mv.addObject(ajaxResp);
            return mv;
        }
    }
     @RequestMapping("/updateUser")
    public ModelAndView updateUser(@RequestParam("id") String id,@RequestParam("username") String username,@RequestParam("password") String password){
        AjaxResponse ajaxResp=new AjaxResponse();
        ModelAndView mv=new ModelAndView("defaultJsonView");
        User user=new User();
         user.setId(id);
         user.setUsername(username);
         user.setPassword(password);
         userService.editUpdate(user);
         ajaxResp.setAjaxResult(AjaxResponse.SUCCESS);
         ajaxResp.setRespObj(user);
         mv.addObject(ajaxResp);
         return mv;

    }
    @RequestMapping("/delUser")
    public ModelAndView delUser(@RequestParam("ids") String ids){
         AjaxResponse ajaxResp=new AjaxResponse();
         ModelAndView mv=new ModelAndView("defaultJsonView");
         String []tempIds=ids.split(",");
         for(String id:tempIds){
             if(userService.isAdmin(id)){
                 ajaxResp.setAdminId(id);
                 continue;
             }
             User user=new User();
             user.setId(id);
             userService.delete(user);
         }
        ajaxResp.setAjaxResult(AjaxResponse.SUCCESS);
        mv.addObject(ajaxResp);
        return mv;
    }
}
