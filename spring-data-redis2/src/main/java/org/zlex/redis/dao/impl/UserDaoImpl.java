/**
 * Aug 29, 2012
 */
package org.zlex.redis.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.zlex.redis.dao.UserDao;
import org.zlex.redis.domain.User;

/**
 * 
 * @author 梁栋
 * @version 1.0
 * @since 1.0
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@Autowired
	private RedisTemplate<Serializable, Serializable> redisTemplate;

	@Override
	public void save(final User user) {
		redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte[] key = redisTemplate.getStringSerializer().serialize("uc.user.info.uid." + user.getUid());
				BoundHashOperations<Serializable, byte[], byte[]> boundHashOperations = redisTemplate.boundHashOps(key);
				boundHashOperations.put(redisTemplate.getStringSerializer().serialize("mobile"), redisTemplate.getStringSerializer().serialize(user.getMobile()));
				boundHashOperations.put(redisTemplate.getStringSerializer().serialize("address"), redisTemplate.getStringSerializer().serialize(user.getAddress()));
				boundHashOperations.put(redisTemplate.getStringSerializer().serialize("postCode"), redisTemplate.getStringSerializer().serialize(user.getPostCode()));
				connection.hMSet(key, boundHashOperations.entries());
				return null;
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.zlex.redis.dao.UserDao#read(java.lang.String)
	 */
	@Override
	public User read(final String uid) {
		return redisTemplate.execute(new RedisCallback<User>() {
			@Override
			public User doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte[] key = redisTemplate.getStringSerializer().serialize("uc.user.info.uid." + uid);
				if (connection.exists(key)) {
					List<byte[]> value = connection.hMGet(key,redisTemplate.getStringSerializer().serialize("address"),
							                                  redisTemplate.getStringSerializer().serialize("mobile"),
							                                  redisTemplate.getStringSerializer().serialize("postCode"));
					User user = new User();
					String address = redisTemplate.getStringSerializer().deserialize(value.get(0));
					user.setAddress(address);
					String mobile = redisTemplate.getStringSerializer().deserialize(value.get(1));
					user.setMobile(mobile);
					String postCode = redisTemplate.getStringSerializer().deserialize(value.get(2));
					user.setPostCode(postCode);
					user.setUid(uid);

					return user;
				}
				return null;
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.zlex.redis.dao.UserDao#delete(java.lang.String)
	 */
	@Override
	public void delete(final String uid) {
		redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) {
				connection.del(redisTemplate.getStringSerializer().serialize("uc.user.info.uid." + uid));
				return null;
			}
		});
	}
}
