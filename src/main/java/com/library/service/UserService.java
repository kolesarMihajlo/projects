package com.library.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.library.DTO.UserDTO;
import com.library.model.User;

public interface UserService {
	
	public User findOne(Long id);
	public Page<User> findAll(int page);
	public User save(User user);
	public void remove(Long id);
	public UserDTO deleteLogical(Long id);
	public List<User> findAllAll();
	public User findOne(String username);
	
	
}
