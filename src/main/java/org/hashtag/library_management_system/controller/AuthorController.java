package org.hashtag.library_management_system.controller;

import java.util.List;

import org.hashtag.library_management_system.entity.Author;
import org.hashtag.library_management_system.entity.ResponseStructure;
import org.hashtag.library_management_system.service.AuthorService;
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
@RequestMapping("author")
public class AuthorController {
	
	@Autowired
    private AuthorService authorService;

    @PostMapping("/save")
    public ResponseEntity<ResponseStructure<Author>> saveAuthor(@RequestBody Author author) {
        return  authorService.saveAuthor(author);
    }

    @GetMapping("/authors")
    public ResponseEntity<ResponseStructure<List<Author>>> getAllAuthors() {
        return authorService.getAllAuthors();
        
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Author>> getAuthorById(@PathVariable int id) {
        return authorService.getAuthorById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<Void>> deleteAuthor(@PathVariable int id) {
           return authorService.deleteAuthor(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseStructure<Author>> updateAuthor(@RequestBody Author author, @PathVariable int id) {
        return authorService.updateAuthor(author, id);
    }
}