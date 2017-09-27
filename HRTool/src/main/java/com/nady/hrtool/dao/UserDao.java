package com.nady.hrtool.dao;

import java.util.List;

import com.nady.hrtool.model.User;

public interface UserDao {

	User findById(int id);

	User findByUserName(String userName);

	void save(User user);

	void saveOrUpdate(User user);

	void deleteByUserName(String userName);

	List<User> findAllUsers();

}
