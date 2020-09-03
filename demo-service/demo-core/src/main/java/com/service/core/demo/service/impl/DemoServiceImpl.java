package com.service.core.demo.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.core.demo.dao.DemoDao;
import com.service.core.demo.dao.entity.UserEntity;
import com.service.core.demo.datatypes.UserData;
import com.service.core.demo.exception.DemoServiceException;
import com.service.core.demo.service.DemoService;
import com.service.core.demo.util.AbstractMapper;

/**
 * Implementation class to define the service interface.
 * 
 * @author Sonali Kate
 */
@Service
public class DemoServiceImpl implements DemoService {

	@Autowired
	private DemoDao demoDao;

	@Override
	public UserData getUser(Long userId) {
		return AbstractMapper.map(demoDao.getUser(userId), UserData.class);
	}

	@Override
	public boolean addUser(UserData userData) {
		Boolean success = Boolean.FALSE;
		UserEntity persistedEntity = demoDao.addUser(AbstractMapper.map(userData, UserEntity.class));
		if (Objects.nonNull(persistedEntity)) {
			success = Boolean.TRUE;
		}
		return success;
	}

	@Override
	public boolean deleteUser(Long userId) {
		Boolean success = Boolean.FALSE;
		try {
			demoDao.deleteUser(userId);
			success = Boolean.TRUE;
		} catch (IllegalArgumentException e) {
			throw new DemoServiceException("Error while deleting user :: " + userId, e);
		}
		return success;
	}

	@Override
	public List<UserData> getUsers() {
		return AbstractMapper.mapAll(demoDao.getUsers(), UserData.class);
	}

}
