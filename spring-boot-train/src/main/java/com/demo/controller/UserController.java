package com.demo.controller;

import com.demo.ajax.AjaxObject;
import com.demo.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 控制器
 */
@EnableAutoConfiguration
@RestController
@RequestMapping("/user")
public class UserController {

   static List<User> userList;

    static{
        userList=new ArrayList<User>();
        userList.add(new User(1l,"zhangsan","zhangsan@gmail.com"));
        userList.add(new User(2l,"lisi","aaaa@gmail.com"));
        userList.add(new User(3l,"wangwu","wangwu@gmail.com"));
    }

    @RequestMapping("/list")
    private AjaxObject list() {
        AjaxObject ajaxObject=new AjaxObject();
        ajaxObject.setStatus(Boolean.TRUE);
        ajaxObject.setMsg("success");
        ajaxObject.setData(userList);

        return ajaxObject;
    }


    @RequestMapping("/{id}")
    private AjaxObject view(@PathVariable("id") Long id) {
        AjaxObject ajaxObject=new AjaxObject();
        User existedUser=null;
        for(User u:userList){
            if(id==u.getId()){
                existedUser=u;
            }
        }
        if(null!=existedUser){
            ajaxObject.setStatus(Boolean.TRUE);
            ajaxObject.setMsg("success");
            ajaxObject.setData(existedUser);
        }else{
            ajaxObject.setStatus(Boolean.FALSE);
            ajaxObject.setMsg("failed,user doesn't exist!");
        }
        return ajaxObject;
    }

    public static void main(String[] args) {
        SpringApplication.run(UserController.class);
    }
}
