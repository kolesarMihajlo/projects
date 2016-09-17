package com.library;

import java.io.File;
import java.io.FileInputStream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.library.model.Author;
import com.library.model.Book;
import com.library.model.Comment;
import com.library.model.Genre;
import com.library.model.Role;
import com.library.model.User;
import com.library.service.AuthorService;
import com.library.service.BookService;
import com.library.service.CommentService;
import com.library.service.GenreService;
import com.library.service.RoleService;
import com.library.service.UserService;

import javassist.bytecode.stackmap.BasicBlock.Catch;

@Component
public class TestData {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private GenreService genreService;
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private CommentService commService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@PostConstruct
	private void init(){
		byte[] image=null;
		try{
			Resource resource=new ClassPathResource("slika.jpg");
			if(resource.exists()){
				File file=resource.getFile();
				image=new byte[(int) file.length()];
				FileInputStream fileInput=new FileInputStream(file);
				fileInput.read(image);
				fileInput.close();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		Role r1=new Role();
		r1.setRole("USER");
		Role r2=new Role();
		r2.setRole("ADMIN");
		Role r3=new Role();
		r3.setRole("GUEST");
		roleService.save(r1);
		roleService.save(r2);
		roleService.save(r3);
		
		User u1=new User();
		u1.setUsername("qq");
		u1.setPassword("ww");
		u1.setEmail("qq@ww.org");
		u1.addRole(r1);
		u1.addRole(r3);
		u1.setImage(image);
		userService.save(u1);
		
		roleService.save(r1);
		User u2=new User();
		u2.setUsername("admin");
		u2.setPassword("right");
		u2.setEmail("admin@right.com");

		u2.addRole(r3);
		u2.addRole(r1);
		u2.addRole(r2);
		userService.save(u2);
		
		for(int i=0;i<4;i++){
			User u10=new User();
			u10.setUsername("user"+i);
			u10.setEmail("user"+i+"@mail"+i+".com");
			u10.setDeleted(false);
			u10.setPassword("passOf"+u10.getUsername());
			u10.addRole(r2);
			u10.setImage(image);
			userService.save(u10);
		}
		
		
		Book b1=new Book();
		b1.setTitle("Stranger");
		Author a1=new Author();
		a1.setFirstname("Albert");
		a1.setLastname("Kami");
		//////////////////////////////////////////
		Comment c1=new Comment();
		c1.setComment("some long text but not really long that should be Comment");
		c1.setUser(u1);
		//////////////////////////////////////
		Genre g1=new Genre();
		g1.setDescription("Author is most likely dead");
		g1.setGenre("Classic");
		
		
		b1.addAuthor(a1);
		b1.addGenre(g1);
	
		
		authorService.save(a1);
		genreService.save(g1);
		
		Book b4=bookService.save(b1);
		c1.setBook(b1);
		
		
		commService.save(c1);
		
		//////////////whole new book and genre etc/////////////////////
		Comment c2=new Comment();
		c2.setComment("One more NEO comment");
		c2.setUser(u2);
		Comment c5=new Comment();
		c5.setComment("One More Comment");
		c5.setUser(u2);
		
		Genre g2=new Genre();
		g2.setGenre("Science Fiction");
		g2.setDescription("Fun stuff");
		
		Book b3=new Book();
		b3.setTitle("Some Random Title");
		

		b3.addGenre(g2);
				
		Author a2=new Author();
		a2.setFirstname("Victor");
		a2.setLastname("Hugo");
		Author a3=new Author();
		a3.setFirstname("Andzej");
		a3.setLastname("Sapkovski");
		b3.addAuthor(a2);
		b3.addAuthor(a3);

		b1.addAuthor(a2);
		b3.addGenre(g2);
		
		
		
		authorService.save(a2);
		authorService.save(a3);
		genreService.save(g2);

		bookService.save(b3);	
		c2.setBook(b3);
		c5.setBook(b3);
		commService.save(c2);
		commService.save(c5);
		
		Book b5=new Book();
		b5.setTitle("Krv vilenjakoh");
		b5.addGenre(g2);
		b5.addGenre(g1);
		b5.addAuthor(a3);
		bookService.save(b5);

		
	
		
		
	}

}
