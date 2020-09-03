package com.service.core.demo.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity to define user details.
 * 
 * @author Sonali Kate
 *
 */
@Entity
@Table(name = "USERDATA")
public class UserEntity {

	/** unique auto generated user id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USERID")
	private Long userId;

	/** user name. */
	@Column(name = "USERNAME")
	private String userName;

	/** user role. */
	@Column(name = "ROLE")
	private String role;

	/** get user id. */
	public Long getUserId() {
		return userId;
	}

	/** set user id. */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/** get user name. */
	public String getUserName() {
		return userName;
	}

	/** set user name. */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/** get user role. */
	public String getRole() {
		return role;
	}

	/** set user role. */
	public void setRole(String role) {
		this.role = role;
	}

	/** default constructor. */
	public UserEntity() {

	}

	/** parameterized constructor. */
	public UserEntity(String userName, String role, boolean syncStatus) {
		this.userName = userName;
		this.role = role;
	}

	/** parameterized constructor. */
	public UserEntity(Long userId, String userName, String role, boolean syncStatus) {
		this.userId = userId;
		this.userName = userName;
		this.role = role;
	}
}
