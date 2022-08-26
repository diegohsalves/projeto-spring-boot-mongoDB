package com.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workshopmongo.domain.User;
import com.workshopmongo.dto.UserDTO;
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
	
	public void insert(User user) {
		repository.insert(user);
	}
	
	public User fromDTO(UserDTO userDTO) {
		return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
	}
	
	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public User update(User user) {
		User newUser = repository.findById(user.getId()).orElse(null);
		updateData(newUser, user);
		return repository.save(newUser);
		}

	public void updateData(User newUser, User user) {
		newUser.setId(user.getId());
		newUser.setName(user.getName());
		newUser.setEmail(user.getEmail());
		
	}
}
