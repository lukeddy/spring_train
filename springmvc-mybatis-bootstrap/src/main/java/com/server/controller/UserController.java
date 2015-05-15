package com.server.controller;

import com.server.model.User;
import com.server.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * 用户管理控制器
 */
@Controller
@RequestMapping(value="/sys")
public class UserController extends BaseController {

    public static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;


    /**
     * 用户管理页面
     * @param model
     * @return
     */
    @RequestMapping(value="/userManagement")
    public String userManagement(
            ModelMap model
    ){
        initData(model);
        return "user_management";
    }

    /**
     * 列表页公共方法
     * @param modelMap
     */
    private void initData(ModelMap modelMap){
        List<User> userList= userService.findAll();
        modelMap.addAttribute("userList",userList);
    }

    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public String doAddUser(
            User user,
            RedirectAttributes redirectAttributes
            ){
        try{
            if(userService.isUserNameExist(user.getUsername())){
                logger.error("username "+user.getUsername()+" already existed, failed!");
                redirectAttributes.addFlashAttribute("fail_msg", "用户名'"+user.getUsername()+"'已经存在,请换一个！");
            }else{
                userService.addUser(user);
                redirectAttributes.addFlashAttribute("suc_msg", "添加系统用户成功!");
            }
        }catch(Exception e){
            logger.error("add sys user failed!", e);
            redirectAttributes.addFlashAttribute("fail_msg", "添加系统用户失败！");
        }

        return "redirect:/sys/userManagement";
    }


    /**
     * 修改密码
     * @param newPwd
     * @param id
     * @return
     */
    @RequestMapping(value="/changePwd")
    public String changePwd(
        @RequestParam(value="newPwd")String newPwd,
        @RequestParam(value="id")int id,
        RedirectAttributes redirectAttributes
    ){
        try{
            userService.changePwd(newPwd,id);
            redirectAttributes.addFlashAttribute("suc_msg", "修改密码成功!");
        }catch(Exception e){
            logger.error("update active code failed!", e);
            redirectAttributes.addFlashAttribute("fail_msg", "修改密码失败！");
        }

        return "redirect:/sys/userManagement";
    }


    /**
     * 锁定或解锁用户
     * @param lockState
     * @param id
     * @return
     */
    @RequestMapping(value="/changeLockState")
    public String changeLockState(
            @RequestParam(value="lockState")int lockState,
            @RequestParam(value="id")int id,
            RedirectAttributes redirectAttributes
    ){
        try{
            userService.changeLockState(lockState,id);

            redirectAttributes.addFlashAttribute("suc_msg", lockState == 0 ? "解锁用户成功！" : "锁定用户成功！");
        }catch(Exception e){
            logger.error("change user lockstate failed");
            redirectAttributes.addFlashAttribute("fail_msg", lockState == 0 ? "解锁用户失败！" : "锁定用户失败！");
        }

        return "redirect:/sys/userManagement";
    }

    @RequestMapping("/testerror")
    public String testError(){
        throw new RuntimeException("服务端出错了");
    }

}
