package com.tangzq.mybatis.service;

import java.util.List;

import com.tangzq.mybatis.domain.User;
import com.tangzq.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhuc
 * @create 2013-3-11 下午1:19:03
 */
@Service("userService")
//@Transactional(value = "isap", rollbackFor = Exception.class)
public class UserService extends BaseMySqlService {

	@Autowired
	private UserMapper userMapper;

	/**
	 * @param id
	 * @return
	 */
	public User get(Integer id) {
		return userMapper.get(id);
	}

	/**
	 * @param id
	 * @return
	 */
	public User get2(Integer id) {
		return userMapper.get2(id);
	}

	/**
	 * @return
	 */
	public List<User> findAll() {
		return userMapper.findAll();
	}

	public void insert(User user){
		userMapper.insert(user);
	}


	/**
	 * @param user
	 * @throws Exception 
	 */
	public void insertWithException(User user) throws Exception {
		userMapper.insert(user);
		throw new Exception("testException");
	}
}
