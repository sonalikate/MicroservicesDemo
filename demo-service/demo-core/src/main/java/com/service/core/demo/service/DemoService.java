
package com.service.core.demo.service;

import java.util.List;

import com.service.core.demo.datatypes.UserData;

/**
 * Interface to define service level operations.
 * 
 * @author Sonali Kate
 */
public interface DemoService {

	UserData getUser(Long userId);

	boolean addUser(UserData userData);

	boolean deleteUser(Long userId);

	List<UserData> getUsers();
}
