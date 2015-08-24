package com.server.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 使用spring AOP进行操作日志记录
 */
public class OperationLogService {
	public static final Log logger = LogFactory.getLog(OperationLogService.class);
	
	@Autowired 
    private HttpServletRequest request;
	
	public void before(JoinPoint joinPoint) {
        if(joinPoint.getSignature().getName().toLowerCase().contains("initbinder")){
            logger.info(" begin invoke "+joinPoint.getSignature().getName());
        }
	}

	public void after(JoinPoint joinPoint) {
        if (!joinPoint.getSignature().getName().toLowerCase().contains("initbinder")) {
            logger.info(" invoke " + joinPoint.getSignature().getName() + " success!");
        }
	}
}
