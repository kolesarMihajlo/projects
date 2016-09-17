package com.library.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.library.model.Author;

public interface AuthorService {
	
	public Author findOne(Long id);
	public List<Author> findAll();
	public Author save(Author author);
	public void remove(Long id);
	public Page<Author> findAll(String search,int page);

}
