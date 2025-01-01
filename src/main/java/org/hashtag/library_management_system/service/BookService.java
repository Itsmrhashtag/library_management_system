package org.hashtag.library_management_system.service;

import java.util.List;
import java.util.Optional;

import org.hashtag.library_management_system.entity.Book;
import org.hashtag.library_management_system.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
	

	    @Autowired
	    private BookRepo bookRepo;

	    public Book saveBook(Book book) {
	        return bookRepo.save(book);
	    }

	    public List<Book> getAllBooks() {
	        return bookRepo.findAll();
	    }

	    public Book getBookById(int id) {
	        Optional<Book> book = bookRepo.findById(id);
	        return book.orElse(null);
	    }

	    public void deleteBook(int id) {
	        bookRepo.deleteById(id);
	    }

	    public Book updateBook(Book book, int id) {
	        Optional<Book> existingBook = bookRepo.findById(id);
	        if (existingBook.isPresent()) {
	            Book updatedBook = existingBook.get();
	            updatedBook.setTitle(book.getTitle());
	            updatedBook.setGenre(book.getGenre());
	            updatedBook.setAuthor(book.getAuthor());
	            return bookRepo.save(updatedBook);
	        }
	        return null;
	    }
	
	
}
