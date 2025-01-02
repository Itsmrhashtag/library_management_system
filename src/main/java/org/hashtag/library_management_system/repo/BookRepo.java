package org.hashtag.library_management_system.repo;

import java.util.List;

import org.hashtag.library_management_system.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book, Integer>{
	
	public List<Book> findBookByGenre(String genre);

}
