package org.hashtag.library_management_system.dao;

import java.util.List;
import java.util.Optional;

import org.hashtag.library_management_system.entity.Author;
import org.hashtag.library_management_system.repo.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorDao {
	@Autowired
	private AuthorRepo authorRepo;
	
	public Author saveAuthor(Author author) {
		return authorRepo.save(author);
	}
	
	public List<Author> getAllAuthor(){
		return authorRepo.findAll();
	}
	
	public Author getAuthorById(int id) {
		Optional<Author> author=authorRepo.findById(id);
		if(author.isPresent()) {
			Author presentAuthor=author.get();
			return presentAuthor;
		}
		else {
			return null;
		}
		
	}
	public void deleteAuthor(int id) {
		authorRepo.deleteById(id);
	}
	
	public Author updateAuthor(Author author,int id) {
		Optional<Author> exAuthor=authorRepo.findById(id);
		if(exAuthor.isPresent()) {
			Author updatedAuthor=exAuthor.get();
			updatedAuthor.setName(author.getName());
			authorRepo.save(updatedAuthor);
			return updatedAuthor;
		}
		else return null;
	}
}
