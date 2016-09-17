package com.library.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.library.model.Role;
import com.library.model.User;
import com.library.service.UserService;

public class UserServiceDetails implements UserDetailsService {
	
//	@Autowired
	private UserService userService;
	

	public UserServiceDetails(UserService userService) {
		super();
		this.userService = userService;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userService.findOne(username);
		System.out.println("loadByusername method");
		
		if(user!=null){
//			System.out.println(user.getEmail());
//			System.out.println(user.getUsername()+" ; "+user.getPassword());
//			System.out.println("roles: "+user.getRoles());
			List<GrantedAuthority> authorities=new ArrayList<>();
			for(Role r:user.getRoles()){
				if(r!=null){
					authorities.add(new SimpleGrantedAuthority(r.getRole()));
				}
			}
//			System.out.println(user.getUsername()+" ; "+user.getPassword()+"   :   "+user.getRoles());
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),true,!user.isDeleted(),true,true,authorities);
		}
		throw new UsernameNotFoundException("User ' "+username+"' not found");
	}

}
