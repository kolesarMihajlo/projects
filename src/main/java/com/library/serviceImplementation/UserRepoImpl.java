package com.library.serviceImplementation;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.library.DTO.UserDTO;
import com.library.model.User;
import com.library.repository.UserRepository;
import com.library.service.UserService;
import com.library.support.UserToUserDTO;

@Service
@Transactional
public class UserRepoImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserToUserDTO toDTO;
	
	@Autowired
	private UserSpecification specUser;
	
//	private Specifications<User> spec=
	
	
	@Override
	public User findOne(Long id) {
		// TODO Auto-generated method stub
		return userRepo.findOne(Specifications.where(specUser.idNumber(id)).and(specUser.isDeleted()));
	}

	
	//HAHA PERFECT WAY TO TEST Cacheable,
	//DELETE ON FRONT END BUT EVEN IF IT CALLS FUNCTION getAll()
	//IT WILL GET OLD LIST BECAUSE THIS METHOD JUST RETRIVES IT FROM CACHE
	//WHICH DOES NOT REFLECT NEWES CHANGES
	@Override
//	@Cacheable(value="libCache")
	public Page<User> findAll(int page) {
		// TODO Auto-generated method stub
		Specification<User> isDel=specUser.isDeleted();
		return userRepo.findAll(isDel,new PageRequest(page, 3));
	}

	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return userRepo.save(user);
	}

	@Override
	public void remove(Long id) {
		// TODO Auto-generated method stub
		userRepo.delete(id);
	}

	@Override
	public UserDTO deleteLogical(Long id) {
//		User ret=userRepo.findOne(id);
		User user=null;
		userRepo.deleteLogical(id);
		System.out.println("num:"+id);
		user=userRepo.findOne(id);
		
		UserDTO retVal=toDTO.convert(user);
		return retVal;
	}

	@Override
	public List<User> findAllAll() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	@Override
	public User findOne(String username) {
		// TODO Auto-generated method stub
		return userRepo.findOne(specUser.findByUsername(username));
	}


	
	

}
