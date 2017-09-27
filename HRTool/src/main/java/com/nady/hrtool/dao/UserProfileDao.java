package com.nady.hrtool.dao;

import java.util.List;

import com.nady.hrtool.model.UserProfile;

public interface UserProfileDao {

	List<UserProfile> findAll();

	UserProfile findByType(String type);

	UserProfile findById(int id);

	void save(UserProfile userProfile);
}
