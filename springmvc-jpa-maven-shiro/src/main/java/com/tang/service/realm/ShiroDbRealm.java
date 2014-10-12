package com.tang.service.realm;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.tang.entity.Permission;
import com.tang.entity.Role;
import com.tang.entity.User;
import com.tang.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * http://kdboy.iteye.com/blog/1169631
 * Apache Shiro 使用手册（四）Realm 实现
 */
public class ShiroDbRealm extends AuthorizingRealm{

	@Resource(name="userService")
	private IUserService userService;

    /**
     * 二、授权实现
       而授权实现则与认证实现非常相似，在我们自定义的Realm中，重载doGetAuthorizationInfo()方法，重写获取用户权限的方法即可。
     * @param principals
     * @return
     */
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
        System.out.println("*************授权实现*******************");
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//获取当前登录的用户名
		String account = (String) super.getAvailablePrincipal(principals);
		
		List<String> roles = new ArrayList<String>();  
		List<String> permissions = new ArrayList<String>();
		User user = userService.getByAccount(account);
		if(user != null){
			if (user.getRoles() != null && user.getRoles().size() > 0) {
				for (Role role : user.getRoles()) {
					roles.add(role.getName());
					if (role.getPmss() != null && role.getPmss().size() > 0) {
						for (Permission pmss : role.getPmss()) {
							if(!StringUtils.isEmpty(pmss.getPermission())){
								permissions.add(pmss.getPermission());
							}
						}
					}
				}
			}
		}else{
			throw new AuthorizationException();
		}
		//给当前用户设置角色
		info.addRoles(roles);
		//给当前用户设置权限
        info.addStringPermissions(permissions); 
		return info;
		
	}

	/**
	 *  认证回调函数,登录时调用.
     *  一、认证实现
     正如前文所提到的，Shiro的认证过程最终会交由Realm执行，这时会调用Realm的getAuthenticationInfo(token)方法。
     该方法主要执行以下操作:
     1、检查提交的进行认证的令牌信息
     2、根据令牌信息从数据源(通常为数据库)中获取用户信息
     3、对用户信息进行匹配验证。
     4、验证通过将返回一个封装了用户信息的AuthenticationInfo实例。
     5、验证失败则抛出AuthenticationException异常信息。
     而在我们的应用程序中要做的就是自定义一个Realm类，继承AuthorizingRealm抽象类，重载doGetAuthenticationInfo ()，重写获取用户信息的方法。
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
        System.out.println("*************认证实现*******************");
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		User user = userService.getByAccount(token.getUsername());
		if (user != null) {
			return new SimpleAuthenticationInfo(user.getAccount(), user
					.getPassword(), user.getNickname());
		} else {
			return null;
		}
	}
}

