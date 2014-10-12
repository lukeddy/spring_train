package com.tang.service;

import com.tang.entity.Permission;
import com.tang.entity.Role;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 10/22/13
 * Time: 1:35 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IRoleService extends IBaseService{
    public boolean add(Role role);
    public List<Role> findAll();
    public Role findByName(String name);
}
