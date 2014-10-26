package com.tzq.dao.impl;

import com.tzq.common.dao.impl.BaseDao;
import com.tzq.dao.FileInfoDAO;
import com.tzq.domain.FileInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FileInfoDaoImpl extends BaseDao implements FileInfoDAO {
   public FileInfo queryById(int id){
       List list=  this.queryAll(" from FileInfo f where f.id="+id);
       if(null!=list&&list.size()>0){
           return (FileInfo)list.get(0);
       }else {
           return null;
       }
   }
//   public void deleteById(int id){
//       FileInfo fileInfo=queryById(id);
//       if(null!=fileInfo){
//           this.delete(fileInfo);
//       }
//   }
}