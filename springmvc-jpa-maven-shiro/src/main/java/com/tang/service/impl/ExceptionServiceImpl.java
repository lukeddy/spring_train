package com.tang.service.impl;

import com.tang.entity.TException;
import com.tang.service.IBaseService;
import com.tang.service.IExceptionService;
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
@Service("exceptionService")
public class ExceptionServiceImpl extends DefultBaseService implements IExceptionService{
    @Resource(name="baseService")
    private IBaseService baseService;


    @Override
    public boolean add(TException tException) {
        boolean flag = false;
        try {
            baseService.save(tException);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<TException> findAll() {
        return baseService.getAll(TException.class);
    }


}
