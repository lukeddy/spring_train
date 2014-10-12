package com.tang.service;

import com.tang.entity.Permission;
import com.tang.entity.Role;
import com.tang.entity.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 10/22/13
 * Time: 1:27 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IPermissionService extends IBaseService {
    public boolean add(Permission permission);
    public List<Permission> findAll();
    public Permission findByName(String pName);
}
