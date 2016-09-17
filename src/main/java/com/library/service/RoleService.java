package com.library.service;

import java.util.List;

import com.library.model.Role;

public interface RoleService {
	
	public Role findOne(Long id);
	public List<Role> findAll();
	public Role save(Role role);
	public void delete(Long id);
	public Role findByRole(String role);

}
