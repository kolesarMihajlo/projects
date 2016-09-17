package com.library.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tbl_genre")
public class Genre {
	
	@Id
	@GeneratedValue
	@Column(name="genre_id")
	private Long id;
	@Column
	private String genre;
	
//	This annotation are remnant from point in time when relationship was ManyToOne and OneToMany with Book
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="book_id")
//	@JsonIgnore
//	//////////////////////////////////////
	
	@ManyToMany(mappedBy="genres")
	@JsonIgnore
	private List<Book> books=new ArrayList<>();
	
	@Column
	private String description;
	public Genre() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void addBook(Book book){
		boolean flag=true;
		if(books.isEmpty()){
			this.books.add(book);
			
		}else{
			for (Book b : books) {
				if(b.getTitle().equals(book.getTitle())){
					flag=false;
					break;
				}
			}
			if(flag){
				this.books.add(book);
				if(!book.getGenres().contains(this)){
					book.addGenre(this);
				}
			}
		}
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Genre other = (Genre) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
//	@Override
//	public String toString() {
//		return "Genre [genre=" + genre + ", book=" + book + ", description=" + description + "]";
//	}
//	
	

}
