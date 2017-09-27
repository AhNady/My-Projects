package com.nady.hrtool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nady.hrtool.dao.UserDao;
import com.nady.hrtool.model.User;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao dao;

	@Autowired
    private PasswordEncoder passwordEncoder;
	
	public User findById(int id) {
		return dao.findById(id);
	}

	public User findByUserName(String userName) {
		User user = dao.findByUserName(userName);
		return user;
	}

	public void saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		dao.save(user);
	}

	
	public void deleteUserByUserName(String userName) {
		dao.deleteByUserName(userName);
	}

	public List<User> findAllUsers() {
		return dao.findAllUsers();
	}

	public boolean userName(Integer id, String userName) {
		User user = findByUserName(userName);
		return ( user == null || ((id != null) && (user.getId() == id)));
	}

	@Override
	public boolean isUserNameUnique(Integer id, String userName) {
		User user = findByUserName(userName);
		return ( user == null || ((id != null) && (user.getId() == id)));
	}
	
	public void saveOrUpdateUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		dao.saveOrUpdate(user);
	}
}
