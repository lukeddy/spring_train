package com.tzq.service;

import com.tzq.common.service.BaseService;

public interface UserService extends BaseService {
    public boolean isUserExist(String username);
    public boolean isAdmin(String id);
}