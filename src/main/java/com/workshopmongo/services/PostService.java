package com.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workshopmongo.domain.Post;
import com.workshopmongo.repository.PostRepository;
import com.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repository;
	
	public Post findById(String id) {
		Post post = repository.findById(id).orElse(null);
		
		if(post == null) {
			throw new ObjectNotFoundException("Usuário não encontrado!");
		
		} else {
			return post;
		}
	}
	
	public List<Post> findByTitle(String text) {
		return repository.findByTitleContaining(text);
	}

}
