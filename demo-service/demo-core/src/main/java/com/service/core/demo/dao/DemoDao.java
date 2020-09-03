package com.service.core.demo.dao;

import java.util.List;

import com.service.core.demo.dao.entity.UserEntity;

/**
 * Interface to define data access operations.
 * 
 * @author Sonali Kate
 */
public interface DemoDao {

	UserEntity getUser(Long userId);

	UserEntity addUser(UserEntity entity);

	void deleteUser(Long userId);

	List<UserEntity> getUsers();
}
