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

import com.library.model.Author;
import com.library.service.AuthorService;
import com.library.support.AuthorToAuthorDTO;

@RestController
@RequestMapping(value="/api/authors")
public class AuthorController {
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private AuthorToAuthorDTO toDto;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Author>> fetchAll(@RequestParam(value="search",required=false)String search,
												 @RequestParam(value="page",defaultValue="0") int page){
		Page<Author> authors=authorService.findAll(search, page);
		List<Author> listauthors=authors.getContent();
		
		
		if(authors==null||!authors.hasContent()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.add("total-pages", ""+authors.getTotalPages());
		httpHeaders.add("total-elements", ""+authors.getTotalElements());

		return new ResponseEntity<>(listauthors,httpHeaders,HttpStatus.OK);
				
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Author> fetchOne(@PathVariable Long id){
		Author author=authorService.findOne(id);
		if(author==null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(author,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/{id}",consumes="application/json",method=RequestMethod.PUT)
	public ResponseEntity<Author> edit(@PathVariable Long id,@RequestBody Author autor){
		if(id!=autor.getId()){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Author saved=authorService.save(autor);
		return new ResponseEntity<Author>(saved, HttpStatus.OK);
	}
	
	
	@RequestMapping(method=RequestMethod.POST,consumes="application/json")
	public ResponseEntity<Author> save(@RequestBody Author author){
		Author saved=authorService.save(author);
		
		return new ResponseEntity<Author>(saved,HttpStatus.OK);
	}

}
