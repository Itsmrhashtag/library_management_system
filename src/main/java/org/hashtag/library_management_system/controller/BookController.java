package org.hashtag.library_management_system.controller;

import java.util.List;

import org.hashtag.library_management_system.entity.Book;
import org.hashtag.library_management_system.entity.ResponseStructure;
import org.hashtag.library_management_system.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("book")
public class BookController {
	

	    @Autowired
	    private BookService bookService;

	    @PostMapping("/save")
	    public ResponseEntity<ResponseStructure<Book>> saveBook(@RequestBody Book book) {
	        Book savedBook = bookService.saveBook(book);
	        if(savedBook!=null) {
	        	ResponseStructure<Book> structure = new ResponseStructure<>(
		                HttpStatus.CREATED.value(), savedBook, "Book Saved Successfully");
		        return new ResponseEntity<>(structure, HttpStatus.CREATED);
	        }else {
	        	ResponseStructure<Book> structure = new ResponseStructure<>(
		                HttpStatus.BAD_REQUEST.value(), null, "Book Not Saved");
		        return new ResponseEntity<>(structure, HttpStatus.BAD_REQUEST);
	        }
	        
	    }

	    @GetMapping("/books")
	    public ResponseEntity<ResponseStructure<List<Book>>> getAllBooks() {
	        List<Book> books = bookService.getAllBooks();
	        if(books.size()!=0) {
	        	ResponseStructure<List<Book>> structure = new ResponseStructure<>(
		                HttpStatus.OK.value(), books, "Books Retrieved Successfully");
		        return new ResponseEntity<>(structure, HttpStatus.OK);
	        }else {
	        	ResponseStructure<List<Book>> structure = new ResponseStructure<>(
		                HttpStatus.NOT_FOUND.value(), null, "Books Not Found");
		        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
	        }
	        
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<ResponseStructure<Book>> getBookById(@PathVariable int id) {
	        Book book = bookService.getBookById(id);
	        if(book!=null) {
	        	ResponseStructure<Book> structure = new ResponseStructure<>(
		                HttpStatus.OK.value(), book, "Book Retrieved Successfully");
		        return new ResponseEntity<>(structure, HttpStatus.OK);
	        }
	        else {
	        	ResponseStructure<Book> structure = new ResponseStructure<>(
		                HttpStatus.NOT_FOUND.value(), null, "Book Not Found");
		        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
	        }
	        
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<ResponseStructure<Void>> deleteBook(@PathVariable int id) {
	    	Book book= bookService.getBookById(id);
	    	if(book!=null) {
	    		bookService.deleteBook(id);
		        ResponseStructure<Void> structure = new ResponseStructure<>(
		                HttpStatus.OK.value(), null, "Book Deleted Successfully");
		        return new ResponseEntity<>(structure, HttpStatus.OK);
	    	}else {
	    		ResponseStructure<Void> structure = new ResponseStructure<>(
		                HttpStatus.NOT_FOUND.value(), null, "Book Not Found");
		        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
	    	}
	        
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<ResponseStructure<Book>> updateBook(@RequestBody Book book, @PathVariable int id) {
	        Book updatedBook = bookService.updateBook(book, id);
	        if(updatedBook!=null) {
	        	ResponseStructure<Book> structure = new ResponseStructure<>(
		                HttpStatus.OK.value(), updatedBook, "Book Updated Successfully");
		        return new ResponseEntity<>(structure, HttpStatus.OK);
	        }else {
	        	ResponseStructure<Book> structure = new ResponseStructure<>(
		                HttpStatus.NOT_FOUND.value(), updatedBook, "Book Not Found");
		        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
	        }
	    }
	}