package com.library.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.library.DTO.UserDTO;
import com.library.model.User;
import com.library.repository.UserRepository;

@Component
public class UserDTOToUser implements Converter<UserDTO, User> {
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public User convert(UserDTO dto) {
		User user=null;
		if(dto.getId()==null){
			user=new User();
		}else{
			user=userRepo.findOne(dto.getId());
			if(user==null){
				throw new IllegalStateException("Editing non-existant User");
			}
		}
		user.setEmail(dto.getEmail());
		user.setUsername(dto.getUsername());
		user.setImage(dto.getImage());
		user.setRoles(dto.getRoles());
		
		return user;
	}
	
	public List<User> convert(List<UserDTO> dtos){
		List<User> retVal=new ArrayList<>();
		for (UserDTO dto : dtos) {
			retVal.add(convert(dto));
		}
		return retVal;
	}

}
