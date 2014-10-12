package com.tang.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "CMSUser")
public class User implements Serializable {

	private static final long serialVersionUID = 7419229779731522702L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userid")
	private Long id;
	@Column(length = 50, unique = true)
	private String account;
	@Column(length = 100)
	@JsonIgnore	//springmvc生成json不包含此字段
	private String password;
	@Column(length = 50)
	private String nickname;
	
	@ManyToMany(cascade = {CascadeType.PERSIST })
    @LazyCollection(LazyCollectionOption.FALSE) //解决异常jpa org.hibernate.LazyInitializationException: failed to lazily initialize a collection
	@JsonIgnore
	@JoinTable(name = "CMSUserRole", joinColumns = { @JoinColumn(name = "userid", referencedColumnName = "userid") }, 
		inverseJoinColumns = { @JoinColumn(name = "roleid", referencedColumnName = "roleid") })
	private Collection<Role> roles;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	/**
	 * 本函数输出将作为默认的<shiro:principal/>输出.
	 */
	public String toString() {
		return account;
	}
}
