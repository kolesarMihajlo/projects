package com.library.service;

import java.util.List;

import com.library.model.Comment;

public interface CommentService {
	
	public Comment findOne(Long id);
	public List<Comment> findAll();
	public List<Comment> findAll(Long id);
	public Comment save(Comment comment);
	public void remove(Long id);

}
