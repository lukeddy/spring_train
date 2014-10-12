package com.tang.service.impl;

import com.tang.entity.Permission;
import com.tang.entity.Role;
import com.tang.service.IBaseService;
import com.tang.service.IPermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 10/22/13
 * Time: 1:28 PM
 * To change this template use File | Settings | File Templates.
 */
@Service("permissionService")
public class PermissionServiceImpl extends DefultBaseService implements IPermissionService {
    @Resource(name="baseService")
    private IBaseService baseService;
    @Override
    public boolean add(Permission permission) {
        boolean flag = false;
        try {
            baseService.save(permission);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<Permission> findAll() {
        return baseService.getAll(Permission.class);
    }

    @Override
    public Permission findByName(String pName) {
        Permission p = null;
        try {
            p = (Permission) baseService.getUniqueResultByJpql("from Permission as p where p.name=?", pName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }
}
