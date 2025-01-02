package org.hashtag.library_management_system.service;

import java.util.List;
import java.util.Optional;

import org.hashtag.library_management_system.dao.AuthorDao;
import org.hashtag.library_management_system.entity.Author;
import org.hashtag.library_management_system.entity.ResponseStructure;
import org.hashtag.library_management_system.repo.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AuthorService {
	@Autowired
    private AuthorDao authorDao;

    public ResponseEntity<ResponseStructure<Author>> saveAuthor(Author author) {
        Author savedAuthor = authorDao.saveAuthor(author);
        if (savedAuthor != null) {
            ResponseStructure<Author> structure = new ResponseStructure<>(
                    HttpStatus.CREATED.value(), savedAuthor, "Author Saved Successfully");
            return new ResponseEntity<>(structure, HttpStatus.CREATED);
        } else {
            ResponseStructure<Author> structure = new ResponseStructure<>(
                    HttpStatus.BAD_REQUEST.value(), null, "Author Not Saved");
            return new ResponseEntity<>(structure, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<ResponseStructure<List<Author>>> getAllAuthors() {
        List<Author> authors = authorDao.getAllAuthor();
        if (!authors.isEmpty()) {
            ResponseStructure<List<Author>> structure = new ResponseStructure<>(
                    HttpStatus.OK.value(), authors, "Authors Retrieved Successfully");
            return new ResponseEntity<>(structure, HttpStatus.OK);
        } else {
            ResponseStructure<List<Author>> structure = new ResponseStructure<>(
                    HttpStatus.NOT_FOUND.value(), null, "Authors Not Found");
            return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<ResponseStructure<Author>> getAuthorById(@PathVariable int id) {
        Author author = authorDao.getAuthorById(id);
        if (author != null) {
            ResponseStructure<Author> structure = new ResponseStructure<>(
                    HttpStatus.OK.value(), author, "Author Retrieved Successfully");
            return new ResponseEntity<>(structure, HttpStatus.OK);
        } else {
            ResponseStructure<Author> structure = new ResponseStructure<>(
                    HttpStatus.NOT_FOUND.value(), null, "Author Not Found");
            return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<ResponseStructure<Void>> deleteAuthor(@PathVariable int id) {
        Author author = authorDao.getAuthorById(id);
        if (author != null) {
            authorDao.deleteAuthor(id);
            ResponseStructure<Void> structure = new ResponseStructure<>(
                    HttpStatus.OK.value(), null, "Author Deleted Successfully");
            return new ResponseEntity<>(structure, HttpStatus.OK);
        } else {
            ResponseStructure<Void> structure = new ResponseStructure<>(
                    HttpStatus.NOT_FOUND.value(), null, "Author Not Found");
            return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<ResponseStructure<Author>> updateAuthor(@RequestBody Author author, @PathVariable int id) {
        Author updatedAuthor = authorDao.updateAuthor(author, id);
        if (updatedAuthor != null) {
            ResponseStructure<Author> structure = new ResponseStructure<>(
                    HttpStatus.OK.value(), updatedAuthor, "Author Updated Successfully");
            return new ResponseEntity<>(structure, HttpStatus.OK);
        } else {
            ResponseStructure<Author> structure = new ResponseStructure<>(
                    HttpStatus.NOT_FOUND.value(), null, "Author Not Found");
            return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
        }
    }
}
