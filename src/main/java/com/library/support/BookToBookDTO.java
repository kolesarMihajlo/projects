package com.library.support;

import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.library.DTO.BookDTO;
import com.library.model.Book;

@Component
public class BookToBookDTO implements Converter<Book, BookDTO> {
	
	
	
	@Override
	public BookDTO convert(Book book) {
		BookDTO dto=new BookDTO();
//		dto.setAuthors(book.getAuthors());
		
		return null;
	}
	
	public List<BookDTO> convert(List<Book> books){
		List<BookDTO> retVal=null;
		return retVal;
	}

}
