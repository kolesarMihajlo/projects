package com.library.specifications;

import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.library.model.Author;
import com.library.model.Book;
import com.library.model.Comment;
import com.library.model.User;

@Component
public class BooksSpecification {

	public BooksSpecification() {
		// TODO Auto-generated constructor stub
	}
	
	public Specification<Book> searchByTitle(final String title){
		return new Specification<Book>() {

			@Override
			public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				
				return cb.like(root.<String>get("title"), "%"+title+"%");
			}
			
		};
	}
	public static Specification<Book> userDel(){
		return new Specification<Book>() {
			
			public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				Join<Book,Comment> joinComment=root.join("comments",JoinType.INNER);
				Join<Comment,User> joinUser=joinComment.join("user",JoinType.INNER);
				
				return cb.isFalse(joinUser.<Boolean>get("deleted"));
			}

			
		};
		
	}

}
