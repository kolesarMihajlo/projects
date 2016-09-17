package com.library.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "tbl_author")
public class Author {

	@Id
	@GeneratedValue
	@Column(name = "author_id")
	private Long id;
	@Column
	private String firstname;
	@Column
	private String lastname;
	@ManyToMany(mappedBy = "authors",fetch=FetchType.LAZY)
	@JsonIgnoreProperties({"authors","comments","genres"})
	private List<Book> books = new ArrayList<>();

	public Author() {
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

	public void addBook(Book book) {
		//At some point switch to implemented equals for book,author...
		//but for now this is ok
		boolean flag=true;
		if (books.isEmpty()) {
			books.add(book);
		} else {
			for (Book b : books) {
				if (b.getTitle().equalsIgnoreCase(book.getTitle())) {
					flag=false;
					break;
				}
			}
			if(flag){
				books.add(book);
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
		Author other = (Author) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



}
