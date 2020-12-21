package com.library.libraryProject.repository;

import com.library.libraryProject.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
//@RepositoryRestResource(collectionResourceRel = "book", path = "book")
@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    @Query("select b from Book b where b.book_name like %?1%")
    List<Book> searchBooksByName(String name);
}
