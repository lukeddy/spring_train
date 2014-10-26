package com.tzq.service;

import com.tzq.common.service.BaseService;

public interface FileInfoService extends BaseService {
        public void deleteFile(int id);
        public void deleteFile(String ids);
}