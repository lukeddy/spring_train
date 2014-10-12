package tangzq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import tangzq.common.rss.SampleContent;
import tangzq.model.User;
import tangzq.service.UserServiceI;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {

	private UserServiceI userService;

	public UserServiceI getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}


	@RequestMapping("/showUser/{id}")
	public ModelAndView showUser(@PathVariable String id, ModelMap modelMap) {
		User u = userService.getUserById(id);
		modelMap.put("user",u);
		return new ModelAndView("showUser",modelMap);
	}

    @RequestMapping(value="/users.json",method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public  List<User> userList(){
     return userService.getAll();
    }

    @RequestMapping(value = "/user.xml",method = RequestMethod.GET, produces = "application/xml;charset=utf-8")
    @ResponseBody
    public Object userXml(){
        return userService.getUserById("1");
    }

    @RequestMapping(value="/rssfeed", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getFeedInRss() {

        List<SampleContent> items = new ArrayList<SampleContent>();

        SampleContent content  = new SampleContent();
        content.setTitle("Spring MVC Tutorial 1");
        content.setUrl("http://www.mkyong.com/spring-mvc/tutorial-1");
        content.setSummary("Tutorial 1 summary ...");
        content.setCreatedDate(new Date());
        items.add(content);

        SampleContent content2  = new SampleContent();
        content2.setTitle("Spring MVC Tutorial 2");
        content2.setUrl("http://www.mkyong.com/spring-mvc/tutorial-2");
        content2.setSummary("Tutorial 2 summary ...");
        content2.setCreatedDate(new Date());
        items.add(content2);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("rssViewer");
        mav.addObject("feedContent", items);

        return mav;

    }
}
