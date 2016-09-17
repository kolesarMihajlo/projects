package com.library.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.library.model.Role;
import com.library.service.RoleService;

@RestController
@RequestMapping(value="/api/roles")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Role>> getAll(){
		List<Role> roles=roleService.findAll();
		if(roles==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(roles, HttpStatus.OK);
	}
	
	
	

}
