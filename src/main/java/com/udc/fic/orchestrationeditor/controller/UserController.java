package com.udc.fic.orchestrationeditor.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.udc.fic.orchestrationeditor.dto.UserDto;
import com.udc.fic.orchestrationeditor.exception.DuplicatedUserException;
import com.udc.fic.orchestrationeditor.exception.InputValidationException;
import com.udc.fic.orchestrationeditor.exception.UserNotFoundException;
import com.udc.fic.orchestrationeditor.model.User;
import com.udc.fic.orchestrationeditor.service.UserService;
import com.udc.fic.orchestrationeditor.validator.UserValidator;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private DozerBeanMapper mapper;

	@Autowired
	private UserValidator userValidator;

	@RequestMapping(value = "/api/users", method = RequestMethod.GET)
	public ResponseEntity<List<UserDto>> getUsers() {
		List<User> users = userService.findAllUsers();
		List<UserDto> usersDto = new ArrayList<UserDto>();
		for (User user : users) {
			usersDto.add(mapper.map(user, UserDto.class));
		}
		return new ResponseEntity<List<UserDto>>(usersDto, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/users", method = RequestMethod.POST)
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto)
			throws DuplicatedUserException, InputValidationException {
		userValidator.validate(userDto);
		User createdUser = userService.createUser(mapper.map(userDto, User.class));
		return new ResponseEntity<UserDto>(mapper.map(createdUser, UserDto.class), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/api/users/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDto> getUser(@PathVariable("id") long id) throws UserNotFoundException {
		User user = userService.findUserById(id);
		return new ResponseEntity<UserDto>(mapper.map(user, UserDto.class), HttpStatus.OK);
	}

	@RequestMapping(value = "/api/users/{id}", method = RequestMethod.PUT)
	public ResponseEntity<UserDto> updateUser(@PathVariable("id") long id, @RequestBody UserDto userDto)
			throws UserNotFoundException, InputValidationException {
		userDto.setId(id);
		User updatedUser = userService.updateUser(mapper.map(userDto, User.class));
		return new ResponseEntity<UserDto>(mapper.map(updatedUser, UserDto.class), HttpStatus.OK);
	}

	@RequestMapping(value = "/api/users/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<UserDto> deleteUser(@PathVariable("id") long id) throws UserNotFoundException {
		userService.deleteUser(id);
		return new ResponseEntity<UserDto>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/api/user", method = RequestMethod.GET)
	public Principal getPrincipal(Principal user) {
		return user;
	}

}
