package com.tzq.service.impl;

import com.tzq.common.service.impl.BaseServiceImpl;
import com.tzq.domain.User;
import com.tzq.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl extends BaseServiceImpl implements UserService {

    @Override
    public boolean isUserExist(String username) {
        List list=this.queryAll(" from User u where u.username='"+username+"'");
        if(null!=list&&list.size()>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean isAdmin(String id) {
        List list=this.queryAll(" from User u where u.id="+Integer.parseInt(id));
        if(null!=list&&list.size()>0){
            User user=(User)list.get(0);
            if("admin".equals(user.getUsername().toLowerCase())){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}