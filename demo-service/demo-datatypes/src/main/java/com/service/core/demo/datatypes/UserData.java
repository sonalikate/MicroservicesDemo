package com.service.core.demo.datatypes;

import java.io.Serializable;

/**
 * Model to store user information.
 * 
 * @author Sonali Kate
 *
 */
public class UserData implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** unique user id. */
	private Long id;

	/** user name. */
	private String userName;

	/** user role. */
	private String role;

	/** default constructor. */
	public UserData() {
		super();
	}

	/** constructor to create object using name and role. */
	public UserData(String userName, String role) {
		super();
		this.userName = userName;
		this.role = role;
	}

	/** constructor to create object using id, name and role. */
	public UserData(Long id, String userName, String role) {
		super();
		this.id = id;
		this.userName = userName;
		this.role = role;
	}

	/** get user id. */
	public Long getId() {
		return id;
	}

	/** set user id. */
	public void setId(Long id) {
		this.id = id;
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

}
