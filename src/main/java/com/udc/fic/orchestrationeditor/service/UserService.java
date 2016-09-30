package com.udc.fic.orchestrationeditor.service;

import java.util.List;

import com.udc.fic.orchestrationeditor.exception.DuplicatedUserException;
import com.udc.fic.orchestrationeditor.exception.UserNotFoundException;
import com.udc.fic.orchestrationeditor.model.User;

public interface UserService {

	User createUser(User user) throws DuplicatedUserException;

	User findUserById(long id) throws UserNotFoundException;

	List<User> findAllUsers();

	User updateUser(User user) throws UserNotFoundException;

	void deleteUser(long id) throws UserNotFoundException;

	User findUserByUsername(String username) throws UserNotFoundException;;

}
