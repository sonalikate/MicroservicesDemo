
package com.service.core.demo.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.service.core.demo.dao.DemoDao;
import com.service.core.demo.dao.entity.UserEntity;
import com.service.core.demo.dao.repository.DemoRepository;

/**
 * DAO implementation class is used to perform operations on database.
 * 
 * @author Sonali Kate
 */
@Component
public class DemoDaoImpl implements DemoDao {

	@Autowired
	private DemoRepository demoRepository;

	@Override
	public UserEntity getUser(Long userId) {
		UserEntity entity = null;
		Optional<UserEntity> optional = demoRepository.findById(userId);
		if (optional.isPresent()) {
			entity = optional.get();
		}
		return entity;
	}

	@Override
	public UserEntity addUser(UserEntity entity) {
		return demoRepository.save(entity);
	}

	@Override
	public void deleteUser(Long userId) {
		demoRepository.deleteById(userId);
	}

	@Override
	public List<UserEntity> getUsers() {
		return demoRepository.findAll();
	}

}
