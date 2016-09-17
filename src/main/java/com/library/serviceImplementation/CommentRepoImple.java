package com.library.serviceImplementation;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.library.model.Comment;
import com.library.repository.CommentRepository;
import com.library.service.CommentService;
import com.library.specifications.CommentsSpecifications;

@Service
@Transactional
public class CommentRepoImple implements CommentService {
	
	@Autowired
	private CommentRepository commentRepo;
	
	
	
	@Autowired
	private CommentsSpecifications commSpec;
	
	@Override
	public Comment findOne(Long id) {
		// TODO Auto-generated method stub
		return commentRepo.findOne(id);
	}

	@Override
	public List<Comment> findAll() {
		// TODO Auto-generated method stub
		
		
		return commentRepo.findAll(CommentsSpecifications.userDel());	
	}

	@Override
	public Comment save(Comment comment) {
		// TODO Auto-generated method stub
		return commentRepo.save(comment);
	}

	@Override
	public void remove(Long id) {
		// TODO Auto-generated method stub
		commentRepo.delete(id);
	}

	@Override
	public List<Comment> findAll(Long id) {
		// TODO Auto-generated method stub
		return commentRepo.findAll(commSpec.getCommentsForUser(id));
	}

}
