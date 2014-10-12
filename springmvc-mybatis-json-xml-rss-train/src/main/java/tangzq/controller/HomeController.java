package tangzq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import tangzq.model.User;
import tangzq.service.UserServiceI;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 10/16/13
 * Time: 12:50 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class HomeController {

    @Autowired
    private UserServiceI userService;

    @RequestMapping(value = "/")
    public ModelAndView index(ModelMap model){
        List<User> list=userService.getAll();
        model.put("userList",list);
        return new ModelAndView("index").addObject(model);
    }
}
