package com.udc.fic.orchestrationeditor.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udc.fic.orchestrationeditor.exception.DuplicatedUserException;
import com.udc.fic.orchestrationeditor.exception.UserNotFoundException;
import com.udc.fic.orchestrationeditor.model.User;
import com.udc.fic.orchestrationeditor.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Resource
	private UserRepository userRepository;

	@Override
	public User createUser(User user) throws DuplicatedUserException {
		if (userRepository.findByUsername(user.getUsername()) != null)
			throw new DuplicatedUserException(user.getUsername());
		else
			return userRepository.save(user);
	}

	@Override
	public User findUserById(long id) throws UserNotFoundException {
		User foundUser = userRepository.findOne(id);
		if (foundUser == null)
			throw new UserNotFoundException(id);
		else
			return foundUser;
	}

	@Override
	public List<User> findAllUsers() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public User updateUser(User user) throws UserNotFoundException {
		User foundUser = userRepository.findOne(user.getId());
		if (foundUser == null)
			throw new UserNotFoundException(user.getId());
		else {
			if (user.getUsername() != null) foundUser.setUsername(user.getUsername());
			if (user.getPassword() != null) foundUser.setPassword(user.getPassword());
			if (user.getRole() != null) foundUser.setRole(user.getRole());
			if (user.getFirstName() != null) foundUser.setFirstName(user.getFirstName());
			if (user.getLastName() != null) foundUser.setLastName(user.getLastName());
			if (user.getBirthDate() != null) foundUser.setBirthDate(user.getBirthDate());
			if (user.getEmail() != null) foundUser.setEmail(user.getEmail());
			if (user.getPhoneNumber() != null) foundUser.setPhoneNumber(user.getPhoneNumber());
			return userRepository.save(foundUser);
		}
	}

	@Override
	public void deleteUser(long id) throws UserNotFoundException {
		if (userRepository.findOne(id) == null)
			throw new UserNotFoundException(id);
		else
			userRepository.delete(id);
	}

	@Override
	public User findUserByUsername(String username) throws UserNotFoundException {
		User foundUser = userRepository.findByUsername(username);
		if (foundUser == null)
			throw new UserNotFoundException(username);
		else
			return foundUser;
	}

}
