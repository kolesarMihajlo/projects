package com.library.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	@RequestMapping(value = "/api/user")
	public Map<String, Object> user(Authentication user) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Object princi = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// System.out.println("ok it's called ");
		if (user != null)
			System.out.println("principalFIrst: " + user.getName());
		if (princi != null || !princi.equals("anonymousUser"))
			System.out.println("principaSecContext: " + princi.toString());
		if (user != null) {
			System.out.println("principal: " + user.toString());
		}
		
		if (princi instanceof UserDetails) {

			map.put("name", ((User) princi).getUsername());
			Iterator iter=((User)princi).getAuthorities().iterator();
			List<String> roles=new ArrayList<>();
			while(iter.hasNext()){
				GrantedAuthority ga=(GrantedAuthority)iter.next();
				System.out.println(ga.getAuthority());
				roles.add(ga.getAuthority());
			}
			map.put("roles", roles);
			map.put("nonExpired", ((User) princi).isAccountNonExpired());
			System.out.println("pricipal USer:" + ((User) princi).getUsername());
			System.out.println(((User) princi).getAuthorities());

		} else {
			map.put("", "");
		}
		return map;

	}
	
	@RequestMapping("/token")
	@ResponseBody
	public Map<String,String> token(HttpSession session) {
		System.out.println("\n"+session.getId()+"  ;  "+session.getCreationTime()+"\n");
		return Collections.singletonMap("token", session.getId());
	}

}
