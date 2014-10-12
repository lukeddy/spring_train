package com.tang.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.tang.dao.util.Pagination;
import com.tang.entity.User;
import com.tang.service.IUserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private static final Log LOG = LogFactory.getLog(UserController.class);
    private static final int PAGE_SIZE=2;
	@Resource(name="userService")
	private IUserService userService;


    @RequestMapping(value = "/registForm")
    public ModelAndView registForm() {
        LOG.info("添加用户页");
        return new ModelAndView("register").addObject(new User());
    }

//	@RequestMapping(value = "/register",method=RequestMethod.POST)
//	@ResponseBody
//	@RequiresRoles("administrator")
//	public boolean register(User user){
//
//		return userService.register(user);
//	}

    @RequestMapping(value = "/register",method=RequestMethod.POST)
	public String register(User user,RedirectAttributes redirectAttributes){

        if(userService.isUserExist(user.getAccount())){
            redirectAttributes.addFlashAttribute("message","注册失败,用户已经存在");
        }else{
            userService.register(user);
            redirectAttributes.addFlashAttribute("message","注册成功");
        }

        return "redirect:/user/registForm";
	}

    @RequestMapping(value="/list")
    public ModelAndView listUser(ModelMap modelMap){
        List<User> users=userService.getAll(User.class);
        modelMap.put("userList",users);
        Pagination<User> page = userService.getPagination(User.class,null,"order by account asc",1,PAGE_SIZE);
        modelMap.addAttribute("page", page);
        return new ModelAndView("user/userList",modelMap);
    }

    @RequestMapping(value="/list/{currentPage}")
    public ModelAndView listUserByPagination(@PathVariable("currentPage") int currentPage,ModelMap modelMap){
        List<User> users=userService.getAll(User.class);
        modelMap.put("userList",users);
        Pagination<User> page = userService.getPagination(User.class,null,"order by account asc",currentPage,PAGE_SIZE);
        modelMap.addAttribute("page", page);
        return new ModelAndView("user/userList",modelMap);
    }

    @RequestMapping(value = "/userForm")
    public ModelAndView addUserPage() {
        LOG.info("添加用户页");
        return new ModelAndView("user/userForm").addObject(new User());
    }
    @RequestMapping(value = "/addUser")
    public String saveUser(HttpServletRequest request, @ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
        LOG.info("保存用户");
        if(userService.isUserExist(user.getAccount())){
            redirectAttributes.addFlashAttribute("message","添加失败,用户已经存在");
        }else{
            userService.register(user);
            redirectAttributes.addFlashAttribute("message","添加成功");
        }
        //重定向，防止表单重复提交
        return "redirect:/user/userForm";//相对于当前路径
        //return "redirect:/user/userList";//相对于当前项目根路径
    }

    /**
     * 根据主键查找用户
     */
    @RequestMapping(value = "/profile/{userId}")
    public ModelAndView getUserById(@PathVariable("userId") long id) {
        LOG.info("id is :" + id);
        User user= userService.getById(User.class,id);
        LOG.info("user's name is :" + user.getAccount());
        return new ModelAndView("user/userInfo").addObject(user);
    }

    @RequestMapping(value = "/updateForm/{userid}")
    public ModelAndView updateForm(@PathVariable("userid") long id) {
        LOG.info("添加用户页");
        //modelMap.addAttribute("userInfo",new UserInfo());
        User user = userService.getById(User.class,id);
        return new ModelAndView("user/userUpdateForm").addObject(user);
    }


    //updateUser
    @RequestMapping(value = "/userUpdate")
    public String update(HttpServletRequest request, @ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
        LOG.info("保存用户");
        userService.update(user);
        redirectAttributes.addFlashAttribute("message", "修改成功");
        //重定向，防止表单重复提交
        return "redirect:/user/updateForm/"+user.getId();//相对于当前路径
    }

    @RequestMapping(value = "/del/{userId}")
    public String delById(@PathVariable("userId") long id) {
        LOG.info("id is :" + id);
        userService.delete(User.class,id);
        return "redirect:/user/list";
    }
}
