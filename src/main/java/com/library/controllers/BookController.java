package com.library.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.model.Book;
import com.library.service.BookService;

import ch.qos.logback.classic.net.SyslogAppender;

@RestController
@RequestMapping(value="/api/books")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Book>> findAll(@RequestParam(value="search",required=false) String search,
											  @RequestParam(value="page",defaultValue="0") int page,
											  @RequestParam(value="sortParams",required=false)String sortParam){
		Page<Book> books=bookService.findAll(search,sortParam, page);
		List<Book> listbooks=books.getContent();
		
		for (Book book : listbooks) {
			System.out.println(book.getTitle());
			System.out.println(book.getAuthors().get(0).toString());
		}
		if(books==null&&!books.hasContent()){
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.add("total-pages", ""+books.getTotalPages());
		httpHeaders.add("total-elements", ""+books.getTotalElements());
//		List<Book> listbooks=books.getContent();
		return new ResponseEntity<>(listbooks,httpHeaders,HttpStatus.OK);
	}
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Book> findAll(@PathVariable Long id){
		if(id==0){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Book book=null;
		book=bookService.findOne(id);
		if(book==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(method=RequestMethod.POST,consumes="application/json")
	public ResponseEntity<Book> save(@RequestBody Book book){
		
		Book booksic=bookService.save(book);
		return new ResponseEntity<>(booksic,HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT,consumes = "application/json")
	public ResponseEntity<Book> edit(@RequestBody Book book,@PathVariable Long id){
		if(id!=book.getId()){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Book persisted=bookService.save(book);
		return new ResponseEntity<>(persisted,HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Book> delete(@PathVariable Long id){
		Book toDelete=bookService.remove(id);
//		if(toDelete==null){
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//		bookService.remove(id);
		System.out.println(toDelete.getTitle());
		return new ResponseEntity<Book>(HttpStatus.OK);
	}
	
	
	
	
	

}
