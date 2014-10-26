package com.tzq.dao;

import com.tzq.common.dao.IBaseDao;
import com.tzq.domain.FileInfo;

public interface FileInfoDAO extends IBaseDao {
    public FileInfo queryById(int id);
//    public void deleteById(int id);
}