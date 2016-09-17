package com.library.DTO;

import java.util.ArrayList;
import java.util.List;

public class BookDTO {
	
	private Long id;
	private String title;
	private List<AuthorDTO> authors=new ArrayList<>();
	private List<GenreDTO> genres=new ArrayList<>();
	private List<CommentDTO> commnets=new ArrayList<>();
	public BookDTO() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<AuthorDTO> getAuthors() {
		return authors;
	}
	public void setAuthors(List<AuthorDTO> authors) {
		this.authors = authors;
	}
	public List<GenreDTO> getGenres() {
		return genres;
	}
	public void setGenres(List<GenreDTO> genres) {
		this.genres = genres;
	}
	public List<CommentDTO> getCommnets() {
		return commnets;
	}
	public void setCommnets(List<CommentDTO> commnets) {
		this.commnets = commnets;
	}
	
	

}
