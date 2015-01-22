package com.tangzq.mybatis.service;

import org.springframework.transaction.annotation.Transactional;

/**
 * 基础mysql Service抽象类
 * 使用isap数据源及回滚
 * @author zhuc
 * @create 2013-3-11 下午4:27:33
 */
@Transactional(value = "isap", rollbackFor = Exception.class)
public abstract class BaseMySqlService {

}
