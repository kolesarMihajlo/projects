package com.library.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="tbl_comment")
public class Comment {
	
	@Id
	@GeneratedValue
	@Column(name="comment_id")
	private Long id;
	@ManyToOne
	@PrimaryKeyJoinColumn
	@JsonIgnoreProperties({"deleted","password"})
	private User user;
	
//	@OnDelete(action=OnDeleteAction.CASCADE)
//	@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,property="id")
	@ManyToOne
	@JoinColumn(name = "book_id")
	@JsonIgnoreProperties({"title","authors","genres","comments"})
	private Book book;
	
	@Column(name="comment",columnDefinition="TEXT")
	private String comment;
	
	public Comment() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	

	public Book getBook() {
		return book;
	}
	
//	public void setBook(Book book){
//		this.book=book;
//	}
	public void setBook(Book book) {
		this.book = book;
		Comment temp=null;
		boolean flag=true;
		for(Comment c:book.getComments()){
			if(c.getComment().equals(this.comment)){
				flag=false;
				break;
				
			}
		}
		if(flag){
			book.addComment(this);
		}
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
		Comment other = (Comment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

}
