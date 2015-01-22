package spring;

import com.tangzq.mybatis.domain.User;
import com.tangzq.mybatis.service.UserService;
import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * Created by Administrator on 2015/1/21.
 */
public class TestUserService extends TestBase {

   @Autowired
   private  UserService userService;

    @Test
    public void testFind(){
        System.out.println(userService.get(1));
        System.out.println(userService.get2(1));
    }


    @Test
    public void testIsnsert(){
        User user = new User();
        user.setId(1);
        user.setUserName("John");
        user.setPassword("lovefancy");
        user.setTrueName("wangwu");
        user.setCreateTime(new Date());
        userService.insert(user);	//受事务管理,抛出Exception时将回滚  (rollbackFor)
    }

    @Test
    public void testInsertWithException(){
        User user = new User();
        user.setId(3);
        user.setUserName("Frank");
        user.setPassword("Nice");
        user.setTrueName("She's great");
        user.setCreateTime(new Date());
        try {
            //受事务管理,抛出Exception时将回滚  (rollbackFor)
            userService.insertWithException(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
