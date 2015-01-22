package spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.tangzq.mybatis.domain.User;
import com.tangzq.mybatis.service.FlowService;
import com.tangzq.mybatis.service.UserService;

import java.util.Date;

/**
 * @author zhuc
 * @create 2013-3-11 下午1:47:03
 */
public class MultiTest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = (UserService) ac.getBean("userService");
		System.out.println(userService.get(1));
		System.out.println(userService.get2(1));

		FlowService flowService = (FlowService) ac.getBean("flowService");
		System.out.println(flowService.get("94003d29-a7b0-42f0-839c-fa609b209ff1"));

//		User user = new User();
//		user.setId(1);
//		user.setUserName("John");
//		user.setPassword("lovefancy");
//		user.setTrueName("wangwu");
//		user.setCreateTime(new Date());
//
//		userService.insertWithException(user);	//受事务管理,抛出Exception时将回滚  (rollbackFor)
	}


}
