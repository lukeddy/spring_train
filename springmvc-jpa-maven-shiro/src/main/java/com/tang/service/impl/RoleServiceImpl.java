package com.tang.service.impl;

import com.tang.entity.Role;
import com.tang.entity.User;
import com.tang.service.IBaseService;
import com.tang.service.IRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 10/22/13
 * Time: 1:36 PM
 * To change this template use File | Settings | File Templates.
 */
@Service("roleService")
public class RoleServiceImpl  extends DefultBaseService implements IRoleService{
    @Resource(name="baseService")
    private IBaseService baseService;

    @Override
    public boolean add(Role role) {
        boolean flag = false;
        try {
            baseService.save(role);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<Role> findAll() {
        return baseService.getAll(Role.class);
    }

    @Override
     public Role findByName(String name) {
        Role role = null;
        try {
            role = (Role) baseService.getUniqueResultByJpql("from Role as r where r.name=?", name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return role;
    }
}
