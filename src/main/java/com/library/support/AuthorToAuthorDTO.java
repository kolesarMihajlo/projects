package com.library.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.library.DTO.AuthorDTO;
import com.library.model.Author;

@Component
public class AuthorToAuthorDTO implements Converter<Author, AuthorDTO> {
	
	@Autowired
	private BookToBookDTO toDTO;	
	
	@Override
	public AuthorDTO convert(Author autor) {
		AuthorDTO dto=new AuthorDTO();
		dto.setFirstname(autor.getFirstname());
		dto.setLastname(autor.getLastname());
		dto.setId(autor.getId());
		dto.setBooks(toDTO.convert(autor.getBooks()));
		return null;
	}
	
	public List<AuthorDTO> convert(List<Author> autors){
		List<AuthorDTO> retVal=new ArrayList<>();
		return retVal;
	}

}
