package com.niit.dao;

import com.niit.model.User;

public interface UserDAO {

	boolean registerUser(User user);

	boolean isUsernameValid(String username);

	boolean isEmailValid(String email);

	User login(User user);

	void update(User user);

	User getUserByUsername(String username);

	boolean isUpdatedEmailValid(String email, String username);

}
