package com.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workshopmongo.domain.User;
import com.workshopmongo.repository.UserRepository;
import com.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findById(String id) {
		User user = repository.findById(id).orElse(null);
		
		if(user == null) {
			throw new ObjectNotFoundException("Usuário não encontrado!");
		
		} else {
			return user;
		}
	}
	
}
