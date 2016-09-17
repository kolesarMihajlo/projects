package com.library.service;

import java.util.List;

import com.library.model.Genre;

public interface GenreService {
	
	public Genre findOne(Long id);
	public List<Genre> findAll();
	public Genre save(Genre genre);
	public void remove(Long id);

}
