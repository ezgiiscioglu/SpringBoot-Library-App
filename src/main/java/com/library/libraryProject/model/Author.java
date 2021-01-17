package com.library.libraryProject.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long author_id;

    @NotNull
    @Column(length = 50)
    private String author_name;

    @NotNull
    @Column(length = 1000)
    private String description;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "author")
    private List<Book> books;

    public Author(String author_name, String description) {
        this.author_name = author_name;
        this.description = description;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

}
