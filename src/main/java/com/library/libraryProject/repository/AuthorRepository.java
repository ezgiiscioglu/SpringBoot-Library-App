package com.library.libraryProject.repository;

import com.library.libraryProject.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
    @Query("select a from Author a where a.author_name like %:name%")
    List<Author> findByName(String name);
}
