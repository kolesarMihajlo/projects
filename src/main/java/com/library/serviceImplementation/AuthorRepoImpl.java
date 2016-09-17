package com.library.serviceImplementation;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.library.model.Author;
import com.library.repository.AuthorRepository;
import com.library.service.AuthorService;
import com.library.specifications.AuthorSpecification;

@Service
@Transactional
public class AuthorRepoImpl implements AuthorService {
	
	@Autowired
	private AuthorRepository authorRepo;
	
	@Autowired
	private AuthorSpecification specAuthor;
	
	@Override
	public Author findOne(Long id) {
		// TODO Auto-generated method stub
		return authorRepo.findOne(id);
	}

	@Override
	public List<Author> findAll() {
		// TODO Auto-generated method stub
		return authorRepo.findAll();
	}

	@Override
	public Author save(Author author) {
		// TODO Auto-generated method stub
		return authorRepo.save(author);
	}

	@Override
	public void remove(Long id) {
		// TODO Auto-generated method stub
		authorRepo.delete(id);
	}

	@Override
	public Page<Author> findAll(String search, int page) {
		Page<Author> authors=null;
		if(search==null){
			return authorRepo.findAll(new PageRequest(page, 4));
		}
		return authorRepo.findAll(specAuthor.searchByName(search),new PageRequest(page, 4));
	}

}
