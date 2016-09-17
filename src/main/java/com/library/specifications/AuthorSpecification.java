package com.library.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.library.model.Author;
import com.library.model.Book;

@Component
public class AuthorSpecification {
	
	public Specification<Author> searchByName(final String search){
		return new Specification<Author>() {

			@Override
			public Predicate toPredicate(Root<Author> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				
				return cb.or(cb.like(root.<String>get("firstname"), "%"+search+"%"),
							 cb.like(root.<String>get("lastname"),"%"+search+"%"));
			}
			
		};
	}

}
