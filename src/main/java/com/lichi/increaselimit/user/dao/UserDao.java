package com.lichi.increaselimit.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.lichi.increaselimit.user.entity.User;

public interface UserDao extends JpaRepository<User, Integer>,JpaSpecificationExecutor<User>{

}
