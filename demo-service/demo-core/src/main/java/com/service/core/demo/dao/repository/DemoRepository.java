package com.service.core.demo.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.core.demo.dao.entity.UserEntity;

/**
 * Repository to talk to the tables.
 * 
 * @author Sonali Kate
 *
 */
@Repository
public interface DemoRepository extends JpaRepository<UserEntity, Long> {

}
