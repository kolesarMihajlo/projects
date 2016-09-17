package com.library.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.library.DTO.UserDTO;
import com.library.model.User;

@Component
public class UserToUserDTO implements Converter<User, UserDTO> {

	@Override
	public UserDTO convert(User user) {
		UserDTO dto=new UserDTO();
		dto.setId(user.getId());
		dto.setUsername(user.getUsername());
		dto.setEmail(user.getEmail());
		dto.setImage(user.getImage());
		dto.setRoles(user.getRoles());
		return dto;
	}
	
	public List<UserDTO> convert(List<User> users){
		List<UserDTO> retVal=new ArrayList<>();
		if(users!=null){
			for (User user : users) {
				retVal.add(convert(user));
			}
		}
		return retVal;
	}
	
	

}
