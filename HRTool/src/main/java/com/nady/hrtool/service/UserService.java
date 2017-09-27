package com.nady.hrtool.service;

import java.util.List;

import com.nady.hrtool.model.User;


public interface UserService {
	
	User findById(int id);
	
	User findByUserName(String userName);
	
	void saveUser(User user);	
	
	void deleteUserByUserName(String userName);

	List<User> findAllUsers(); 
	
	boolean isUserNameUnique(Integer id, String userName);

	void saveOrUpdateUser(User user);
}