/**
 * Aug 29, 2012
 */
package org.zlex.redis.domain;

import java.io.Serializable;

/**
 * 
 * @author 梁栋
 * @version 1.0
 * @since 1.0
 */
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1267719235225203410L;

	private String uid;

	private String address;

	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * @param uid
	 *            the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

}
