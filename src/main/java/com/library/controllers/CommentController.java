package com.library.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.library.model.Author;
import com.library.model.Comment;
import com.library.service.CommentService;

@RestController
@RequestMapping(value="/api/comments")
public class CommentController {
	
	@Autowired
	private CommentService commService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Comment>> getAll(){
		List<Comment> comments=commService.findAll();
		return new ResponseEntity<List<Comment>>(comments,HttpStatus.OK);
	}
	@RequestMapping(value="/{id}",method=RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Comment> edit(@PathVariable Long id,@RequestBody Comment comm){
		if(id!=comm.getId()){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Comment saved=commService.save(comm);
		return new ResponseEntity<Comment>(saved,HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST,consumes="application/json")
	public ResponseEntity<Comment> save(@RequestBody Comment comm){
		Comment saved=commService.save(comm);
		
		return new ResponseEntity<Comment>(saved,HttpStatus.OK);
	}
	
	
	public ResponseEntity<Comment> delete(Long id){
		return null;
	}

}
