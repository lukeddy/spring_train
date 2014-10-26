package com.tang.service;

import com.tang.entity.Role;
import com.tang.entity.TException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 10/22/13
 * Time: 1:35 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IExceptionService extends IBaseService{
    public boolean add(TException tException);
    public List<TException> findAll();
}
