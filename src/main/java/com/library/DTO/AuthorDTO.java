package com.library.DTO;

import java.util.ArrayList;
import java.util.List;

public class AuthorDTO {
	
	private Long id;
	private String firstname;
	private String lastname;
	private List<BookDTO> books=new ArrayList<>();
	public AuthorDTO() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public List<BookDTO> getBooks() {
		return books;
	}
	public void setBooks(List<BookDTO> books) {
		this.books = books;
	}
	

}
