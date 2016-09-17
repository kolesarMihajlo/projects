package com.library.serviceImplementation;

import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Component;

import com.library.model.Book;
import com.library.model.Comment;
import com.library.model.Role;
import com.library.model.User;


@Component
public final class UserSpecification {

	public UserSpecification() {
		// TODO Auto-generated constructor stub
	}

	static Specification<User> idNumber(final Long id){
		return new Specification<User>() {
			
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				Long ID=new Long(id);
				return cb.equal(root.<User>get("id"),ID);
			};
		};
		
	}
	
	
	static Specification<User> isDeleted(){
		return new Specification<User>() {
			
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				return cb.isFalse(root.<Boolean>get("deleted"));
			};
		};
		
	}
	
	static Specification<User> findByUsername(final String username){
		return new Specification<User>() {
			
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// Join<Book,Comment> joinComment=root.join("comments",JoinType.INNER);
//				Join<User,Role> joinRole=root.join("roles",JoinType.INNER);
				Fetch<User,Role> fetchRole=root.fetch("roles");
				return cb.equal(root.<User>get("username"), username);
			}};
	}
	
	
//	CriteriaBuilder cb = em.getCriteriaBuilder();
//	CriteriaQuery<Oglas> q = cb.createQuery(Oglas.class);
//	Root<Oglas> c = q.from(Oglas.class);
//	ParameterExpression<Date> date = cb.parameter(Date.class);
//	q.select(c).where(cb.and(cb.greaterThan(c.<Date> get("istekao"), date)));
//	
//	if (sortOrder.equalsIgnoreCase("ASC")) {
//		q.orderBy(cb.asc(c.get(sortBy)));
//	} else {
//		q.orderBy(cb.desc(c.get(sortBy)));
//	}
//	
//	TypedQuery<Oglas> query = em.createQuery(q);
//	query.setParameter(date, current);
//	List<Oglas> result = query.getResultList();
//	return result;
//	cb.or(
//            cb.like(cb.lower(root.<String>get(Todo_.title)), containsLikePattern),
//				cb.like(cb.lower(root.<String>get(Todo_.description)), containsLikePattern)
	
//	 public static Specification<Customer> isLongTermCustomer() {
//		    return new Specification<Customer>() {
//		      public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query,
//		            CriteriaBuilder builder) {
//
//		         LocalDate date = new LocalDate().minusYears(2);
//		         return builder.lessThan(root.get(_Customer.createdAt), date);
//		      }
//		    };
//		  }
	
	
	

}
