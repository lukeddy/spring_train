package com.tangzq.test;


import com.tangzq.model.User;
import com.tangzq.repository.UserRepository;
import com.tangzq.utils.CommonProps;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;

import java.util.List;

public class TestUserRepository extends TestBase {

    @Autowired
    private UserRepository userRepository;

    @Before
    public void init(){
        User u=userRepository.findByUsername(CommonProps.ADMIN_NAME);
        if(null==u){
            u=new User();
            u.setUsername(CommonProps.ADMIN_NAME);
            u.setPassword(DigestUtils.md5DigestAsHex(CommonProps.ADMIN_PWD.getBytes()));
            u.setEmail(CommonProps.ADMIN_EMAIL);
            userRepository.save(u);
            System.out.println("初始化管理员账号成功！");
        }else{
            System.out.println("管理员账号已经存在");
        }
    }


    @Test
    public void testFindAll(){
        List<User> users=userRepository.findAll();
        Assert.isTrue(!users.isEmpty(),"没有用户信息");
        for(User u:users){
            System.out.println(u);
        }
    }

    @Test
    public void testFindByUsername(){
        User user=userRepository.findByUsername(CommonProps.ADMIN_NAME);
        Assert.notNull(user,"");
        System.out.println(user);
    }

    @Test
    public void testFindById(){
        User user=userRepository.findOne("599c1f9077c8cf04cd6b859e");
        Assert.notNull(user,"");
        System.out.println(user);
    }

    @Test
    public void testFindByUsernameAndPwd(){
        User u=userRepository.findByUsernameAndPassword(CommonProps.ADMIN_NAME,
                DigestUtils.md5DigestAsHex(CommonProps.ADMIN_PWD.getBytes()));
        Assert.notNull(u,"");
        System.out.println(u);

        u=userRepository.findByUsernameAndPassword("test",
                DigestUtils.md5DigestAsHex("test".getBytes()));
        Assert.isNull(u,"");
    }


    @Test
    public void testLogin(){
        Assert.isTrue(!isLogin("test","test"),"");
        Assert.isTrue(isLogin(CommonProps.ADMIN_NAME,CommonProps.ADMIN_PWD),"");

    }

    private boolean isLogin(String uname,String pwd){
        User user=userRepository.findByUsernameAndPassword(uname,DigestUtils.md5DigestAsHex(pwd.getBytes()));
        if(null==user){
            System.out.println("账号或者密码错误！");
            return false;
        }else{
            System.out.println("登陆成功！");
            return true;
        }
    }

}
