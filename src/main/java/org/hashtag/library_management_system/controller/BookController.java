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
	        return bookService.saveBook(book);
	    }

	    @GetMapping("/books")
	    public ResponseEntity<ResponseStructure<List<Book>>> getAllBooks() {
	        return bookService.getAllBooks();
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<ResponseStructure<Book>> getBookById(@PathVariable int id) {
	        return bookService.getBookById(id);
	    }
	    @GetMapping("/genre/{genre}")
	    public ResponseEntity<ResponseStructure<Book>> getBookByGenre(@PathVariable String genre) {
	        return bookService.findBookByGenre(genre);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<ResponseStructure<Void>> deleteBook(@PathVariable int id) {
	    		return bookService.deleteBook(id);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<ResponseStructure<Book>> updateBook(@RequestBody Book book, @PathVariable int id) {
	        return bookService.updateBook(book, id);
	    }
	}