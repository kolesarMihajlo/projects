package com.library.serviceImplementation;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.model.Genre;
import com.library.repository.GenreRepository;

@Service
@Transactional
public class GenreRepoImpl implements com.library.service.GenreService{

	@Autowired
	private GenreRepository genreRepo;
	
	@Override
	public Genre findOne(Long id) {
		// TODO Auto-generated method stub
		return genreRepo.findOne(id);
	}

	@Override
	public List<Genre> findAll() {
		// TODO Auto-generated method stub
		return genreRepo.findAll();
	}

	@Override
	public Genre save(Genre genre) {
		// TODO Auto-generated method stub
		return genreRepo.save(genre);
	}

	@Override
	public void remove(Long id) {
		// TODO Auto-generated method stub
		genreRepo.delete(id);
	}

}
