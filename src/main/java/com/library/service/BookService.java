package com.library.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.library.model.Book;

public interface BookService {
	
	public Book findOne(Long id);
	public List<Book> findAll();
	public Book save(Book book);
//	public void remove(Long id);
	public Book remove(Long id);
	
	public Page<Book> findAll(String title,String sortParam,int page);

}
