package com.udc.fic.orchestrationeditor.repository;

import org.springframework.data.repository.CrudRepository;
import com.udc.fic.orchestrationeditor.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByUsername(String username);

}
