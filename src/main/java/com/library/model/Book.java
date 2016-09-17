package com.library.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "tbl_book")
public class Book {

	@Id
	@GeneratedValue
	@Column(name = "book_id")
	private Long id;
	@Column(name = "title")
	private String title;
	// @JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,
	// property="@traineeId")
	// @JsonManagedReference

	@ManyToMany
	@JoinTable(name = "author_book", joinColumns = @JoinColumn(name = "book_id"),
									inverseJoinColumns = @JoinColumn(name = "author_id"))
	private List<Author> authors = new ArrayList<>();

	// cascade=CascadeType.ALL in this concrete case i tested Pesist, I quite
	// powerfull because i don't really have to save individual
	// entity like genre or comment seprately but upon updateing book, where i
	// have ensured that in turn saves this(book) to let's say genre
	// all i need to do i save object book to DB and that automatically saves
	// genre to DB.
	// When .ALL is not used aka .PERSIST then i have to save genre object to DB
	// individually.
	// But don't forget book in this case is parent entity so it must first be
	// saved the to it added genere so genre can get proper foregn key

	// @OneToMany(mappedBy = "book",fetch=FetchType.EAGER)
	// This was initially OneToMany relationship but upon some pondering and
	// head ponding i concluded that it's better to set it
	// to ManyToMany

	@ManyToMany
	@JoinTable(name = "genre_book", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
	private List<Genre> genres = new ArrayList<>();

	@OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
	private List<Comment> comments = new ArrayList<>();

	public Book() {
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

	public void addAuthor(Author author) {
		// if(!authors.contains(author)){
		// authors.add(author);
		// author.addBook(this);
		// }
		// but since my equals work with id's maybe it's not best idea right
		// now, later yes
		boolean flag = true;
		if (authors.isEmpty()) {
			authors.add(author);
			author.addBook(this);
		} else {
			for (Author author1 : authors) {
				if ((author1.getFirstname().equalsIgnoreCase(author.getFirstname()))
						&& (author1.getLastname().equalsIgnoreCase(author.getLastname()))) {
					flag = false;
					break;
				}

			}
			if (flag) {
				authors.add(author);
				author.addBook(this);
			}
		}
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public void addGenre(Genre genre) {
		boolean flag=true;
		if (genre != null) {
			if (genres.isEmpty()) {
				genres.add(genre);
				genre.addBook(this);
				
				/// Pa brate be moze ovo tako jednostavno, mora malo vise
				/// algorimisanja,lol tj pojednostavi okreni logiku prvog ifa u
				/// for petlji

			} else {
				for (Genre g : genres) {
					if (g.getGenre().equals(genre.getGenre())) {
						flag=false;
						break;// mozda break spasi stvari
					}
				}
				if(flag){
					genres.add(genre);
					genre.addBook(this);
				}
			}
		}

	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public void addComment(Comment comment) {
		this.comments.add(comment);
	}
	// public void addComment(Comment comment) {
	// this.comments.add(comment);
	// comment.setBook(this);

	// if (comments.isEmpty()) {
	// comments.add(comment);
	// if (comment.getBook() == null ||
	// !comment.getBook().getTitle().equals(this.getTitle())) {
	// comment.setBook(this);
	// }
	// } else {
	// for (Comment c : comments) {
	// if (!c.getComment().equals(comment.getComment())) {
	// comments.add(comment);
	// if (comment.getBook() == null ||
	// !comment.getBook().getTitle().equals(this.getTitle())) {
	// comment.setBook(this);
	// }
	// }
	// }
	// }
	// }

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
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
		Book other = (Book) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	// @Override
	// public String toString() {
	// return "Book [id=" + id + ", title=" + title + ", authors=" + authors +
	// ", genres=" + genres + ", comments="
	// + comments + "]";
	// }

}
