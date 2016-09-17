package com.library.serviceImplementation;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.model.Role;
import com.library.repository.RoleRepository;
import com.library.service.RoleService;

@Service
@Transactional
public class RoleRepoImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepo;
	
	public RoleRepoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Role findOne(Long id) {
		// TODO Auto-generated method stub
		return roleRepo.findOne(id);
	}

	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return roleRepo.findAll();
	}

	@Override
	public Role save(Role role) {
		// TODO Auto-generated method stub
		return roleRepo.save(role);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		roleRepo.delete(id);
	}

	@Override
	public Role findByRole(String role) {
		// TODO Auto-generated method stub
		return roleRepo.findByRole(role);
	}

}
