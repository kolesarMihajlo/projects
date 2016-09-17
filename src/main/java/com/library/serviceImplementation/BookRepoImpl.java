package com.library.serviceImplementation;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.library.model.Book;
import com.library.repository.BookRepository;
import com.library.service.BookService;
import com.library.specifications.BooksSpecification;
import com.library.specifications.CommentsSpecifications;

@Service
@Transactional
public class BookRepoImpl implements BookService {
	
	@Autowired
	private BookRepository bookRepo;
	
	@Autowired
	private BooksSpecification specBook;
	@Override
	public Book findOne(Long id) {
		// TODO Auto-generated method stub
		return bookRepo.findOne(id);
	}

	@Override
	public Page<Book> findAll(String title,String sortParam,int page) {
//		sortParam=sortByParam+';'+ASCDESC;
		String sortByParam=null;
		String ascDesc=null;
		System.out.println(sortParam);
		if(sortParam!=null){
			String [] arr=sortParam.split(";");
			sortByParam=arr[0];
			ascDesc=arr[1];
			System.out.println(sortByParam);
			System.out.println(ascDesc);
		}
		Sort sort=null;
		if(sortByParam!=null && !sortByParam.equalsIgnoreCase("undefined")){
			if(ascDesc.equalsIgnoreCase("asc")){
				System.out.println(sortByParam);
				sort=new Sort(new Order(Direction.ASC,sortByParam));
			}else{
				sort=new Sort(new Order(Direction.DESC,sortByParam));
			}
		}
		if(title!=null&&sort!=null){
			return bookRepo.findAll(specBook.searchByTitle(title), new PageRequest(page, 2, sort));
		}else if(title!=null&&sort==null){
			return bookRepo.findAll(specBook.searchByTitle(title), new PageRequest(page, 2));
		}else if(title==null&&sort!=null){
			return bookRepo.findAll(new PageRequest(page, 2,sort));
		}else{
			return bookRepo.findAll(new PageRequest(page, 2));
		}
		
	}

	@Override
	public Book save(Book book) {
		// TODO Auto-generated method stub
		return bookRepo.save(book);
	}

	@Override
	public Book remove(Long id) {
		Book book=null;
		book=findOne(id);
		bookRepo.delete(id);
		return book;
		
	}

	@Override
	public List<Book> findAll() {
		// TODO Auto-generated method stub
		return bookRepo.findAll();
	}

}
