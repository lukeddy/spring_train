package com.tzq.service.impl;

import com.tzq.common.service.impl.BaseServiceImpl;
import com.tzq.dao.FileInfoDAO;
import com.tzq.domain.FileInfo;
import com.tzq.service.FileInfoService;
import com.tzq.util.FileUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("fileInfoService")
@Transactional
public class FileInfoServiceImpl extends BaseServiceImpl implements FileInfoService {

    @Autowired
	private FileInfoDAO fileInfoDAO;
    @Override
    public void deleteFile(int id) {
      FileInfo fileInfo=fileInfoDAO.queryById(id);
      this.delete(fileInfo);
      FileUtil.deleteRealFile(fileInfo.getMovieStorePath());
      FileUtil.deleteRealFile(fileInfo.getThumbStorePath());
    }

    @Override
    public void deleteFile(String ids) {
       if(!StringUtils.isEmpty(ids)){
           String[] strIds=ids.split(",");
           for(String id:strIds){
                  deleteFile(Integer.parseInt(id));
           }
       }
    }
}