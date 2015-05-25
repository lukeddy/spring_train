/**
 * Aug 29, 2012
 */
package org.zlex.redis.dao;

import org.zlex.redis.domain.User;

/**
 * 
 * @author 梁栋
 * @version 1.0
 * @since 1.0
 */
public interface UserDao {
	/**
	 * @param uid
	 * @param address
	 */
	void save(User user);

	/**
	 * @param uid
	 * @return
	 */
	User read(String uid);

	/**
	 * @param uid
	 */
	void delete(String uid);
}
