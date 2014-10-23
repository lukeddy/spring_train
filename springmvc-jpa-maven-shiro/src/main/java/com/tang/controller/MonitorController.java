package com.tang.controller;

import com.tang.service.IExceptionService;
import com.tang.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2014/10/23.
 */
@Controller
public class MonitorController {


    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/testMonitor" ,method= RequestMethod.GET)
    public void testMonitor(HttpServletResponse response){
        try {
            userService.insertTest();
        } catch (Exception e) {
            response.setCharacterEncoding("utf-8");
            try {
                PrintWriter out=response.getWriter();
                out.write("测试成功");
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
    }
}
