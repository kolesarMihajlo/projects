package com.library.specifications;

import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.library.model.Comment;
import com.library.model.User;


@Component
public final class CommentsSpecifications {

	public CommentsSpecifications() {
		// TODO Auto-generated constructor stub
	}
	
	public static Specification<Comment> userDel(){
		return new Specification<Comment>() {
			
			public Predicate toPredicate(Root<Comment> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				Join<Comment,User> join=root.join("user",JoinType.INNER);
				
				return cb.isFalse(join.<Boolean>get("deleted"));
			};
		};
		
	}
	public static Specification<Comment> getCommentsForUser(final Long id){
		return new Specification<Comment>() {
			public Predicate toPredicate(Root<Comment>root,CriteriaQuery<?> query,CriteriaBuilder cb){
				Join<Comment,User> join=root.join("user",JoinType.INNER);
				return cb.equal(join.<User>get("id"), id);
			}
		};
	}
	
	
	
//	CriteriaBuilder cb = em.getCriteriaBuilder();
//	CriteriaQuery<Oglas> q = cb.createQuery(Oglas.class);
//	Root<Oglas> c = q.from(Oglas.class);
//	Join<Oglas,Kategorija> p=c.join("kategorija",JoinType.LEFT);
//	ParameterExpression<Date> date = cb.parameter(Date.class);
//	ParameterExpression<String> naziv = cb.parameter(String.class);
////	ParameterExpression<String> naslov=cb.parameter(String.class);
//	
//	
//	
//	q.select(c).where(cb.and(cb.greaterThan(c.<Date> get("istekao"), date),
//							 cb.equal(p.get("naziv"), naziv)));
//	
//	
//	TypedQuery<Oglas> query = em.createQuery(q);
//	query.setParameter(date, current);
//	query.setParameter(naziv, nazivkategorije);
//	List<Oglas> result = query.getResultList();
	/////////////////////////////////////////////////
//	return cb.isFalse(root.<Boolean>get("deleted"));

}
