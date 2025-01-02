package org.hashtag.library_management_system.service;

import java.util.List;

import org.hashtag.library_management_system.dao.BookDao;
import org.hashtag.library_management_system.entity.Book;
import org.hashtag.library_management_system.entity.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BookService {
	

	 @Autowired
	    private BookDao bookDao;

	    public ResponseEntity<ResponseStructure<Book>> saveBook(Book book) {
	        Book savedBook = bookDao.saveBook(book);
	        if(savedBook!=null) {
	        	ResponseStructure<Book> structure = new ResponseStructure<Book>(
		                HttpStatus.CREATED.value(), savedBook, "Book Saved Successfully");
		        return new ResponseEntity<ResponseStructure<Book>>(structure, HttpStatus.CREATED);
	        }else {
	        	ResponseStructure<Book> structure = new ResponseStructure<Book>(
		                HttpStatus.BAD_REQUEST.value(), null, "Book Not Saved");
		        return new ResponseEntity<ResponseStructure<Book>>(structure, HttpStatus.BAD_REQUEST);
	        }
	        
	    }

	    public ResponseEntity<ResponseStructure<List<Book>>> getAllBooks() {
	        List<Book> books = bookDao.getAllBooks();
	        if(books.size()!=0) {
	        	ResponseStructure<List<Book>> structure = new ResponseStructure<List<Book>>(HttpStatus.OK.value(), books, "Books Retrieved Successfully");
		        return new ResponseEntity<ResponseStructure<List<Book>>>(structure, HttpStatus.OK);
	        }else {
	        	ResponseStructure<List<Book>> structure = new ResponseStructure<List<Book>>(HttpStatus.NOT_FOUND.value(), null, "Books Not Found");
		        return new ResponseEntity<ResponseStructure<List<Book>>>(structure, HttpStatus.NOT_FOUND);
	        }
	        
	    }

	    public ResponseEntity<ResponseStructure<Book>> getBookById(int id) {
	        Book book = bookDao.getBookById(id);
	        if(book!=null) {
	        	ResponseStructure<Book> structure = new ResponseStructure<Book>(HttpStatus.OK.value(), book, "Book Retrieved Successfully");
		        return new ResponseEntity<ResponseStructure<Book>>(structure, HttpStatus.OK);
	        }
	        else {
	        	ResponseStructure<Book> structure = new ResponseStructure<Book>(HttpStatus.NOT_FOUND.value(), null, "Book Not Found");
		        return new ResponseEntity<ResponseStructure<Book>>(structure, HttpStatus.NOT_FOUND);
	        }
	        
	    }

	    public ResponseEntity<ResponseStructure<Void>> deleteBook(int id) {
	    	Book book= bookDao.getBookById(id);
	    	if(book!=null) {
	    		bookDao.deleteBook(id);
		        ResponseStructure<Void> structure = new ResponseStructure<Void>(HttpStatus.OK.value(), null, "Book Deleted Successfully");
		        return new ResponseEntity<ResponseStructure<Void>>(structure, HttpStatus.OK);
	    	}else {
	    		ResponseStructure<Void> structure = new ResponseStructure<Void>(HttpStatus.NOT_FOUND.value(), null, "Book Not Found");
		        return new ResponseEntity<ResponseStructure<Void>>(structure, HttpStatus.NOT_FOUND);
	    	}
	        
	    }

	    public ResponseEntity<ResponseStructure<Book>> updateBook(Book book, int id) {
	        Book updatedBook = bookDao.updateBook(book, id);
	        if(updatedBook!=null) {
	        	ResponseStructure<Book> structure = new ResponseStructure<Book>(HttpStatus.OK.value(), updatedBook, "Book Updated Successfully");
		        return new ResponseEntity<ResponseStructure<Book>>(structure, HttpStatus.OK);
	        }else {
	        	ResponseStructure<Book> structure = new ResponseStructure<Book>(HttpStatus.NOT_FOUND.value(), updatedBook, "Book Not Found");
		        return new ResponseEntity<ResponseStructure<Book>>(structure, HttpStatus.NOT_FOUND);
	        }
	    }
	
	    public ResponseEntity<ResponseStructure<List<Book>>> findBookByGenre(String genre){
	    	List<Book> book = bookDao.findBookByGenre(genre);
	        if(book!=null) {
	        	ResponseStructure<List<Book>> structure = new ResponseStructure<List<Book>>(HttpStatus.OK.value(), book, "Book Founded Successfully");
		        return new ResponseEntity<ResponseStructure<List<Book>>>(structure, HttpStatus.OK);
	        }else {
	        	ResponseStructure<List<Book>> structure = new ResponseStructure<List<Book>>(HttpStatus.NOT_FOUND.value(), book, "Book Not Found");
		        return new ResponseEntity<ResponseStructure<List<Book>>>(structure, HttpStatus.NOT_FOUND);
	        }
	    }
	
}
