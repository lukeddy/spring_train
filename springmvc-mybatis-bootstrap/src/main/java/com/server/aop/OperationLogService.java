package com.server.aop;

import com.server.model.User;
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
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user != null&&!joinPoint.getSignature().getName().toLowerCase().contains("initbinder")){
            logger.info(user.getUsername()+" begin invoke "+joinPoint.getSignature().getName());
        }
	}

	public void after(JoinPoint joinPoint) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null&&!joinPoint.getSignature().getName().toLowerCase().contains("initbinder")) {
            logger.info(user.getUsername() + " invoke " + joinPoint.getSignature().getName() + " success!");
        }
	}
}
