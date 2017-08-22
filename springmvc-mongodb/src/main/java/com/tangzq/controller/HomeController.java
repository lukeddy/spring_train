package com.tangzq.controller;

import com.tangzq.model.User;
import com.tangzq.repository.UserRepository;
import com.tangzq.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * 首页控制器
 */
@Controller
public class HomeController {

    @Autowired
    private MyService myService;

    @Autowired
    private UserRepository userRepository;

    /**
     * 跳转到首页
     *
     * @return
     */
    @RequestMapping(value = "/home")
    public String index(
            ModelMap modelMap
    ) {
        modelMap.addAttribute("appName", myService.getAppName());
        return "index";
    }


    @RequestMapping(value = "/users")
    @ResponseBody
    public List<User> userList() {
        List<User> users = userRepository.findAll();
        Assert.isTrue(!users.isEmpty(), "没有用户信息");
        for (User u : users) {
            System.out.println(u);
        }
        return users;
    }
}
