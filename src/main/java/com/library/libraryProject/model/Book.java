package com.library.libraryProject.model;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long book_id;

    @Column(length = 100)
    private String book_name;

    @Column(length = 100)
    private String book_sub_name;

    @Column(length = 100)
    private String book_serial_name;

    @Column(unique = true)
    private String isbn;

    @Column( length = 1000)
    private String description;

    @OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "book")
    private Set<Author> authors = new HashSet<Author>();

    @OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "book")
    private Set<Publisher> publishers = new HashSet<Publisher>();

    public Book(String book_name, String book_sub_name, String book_serial_name, String isbn, String description) {
        this.book_name = book_name;
        this.book_sub_name = book_sub_name;
        this.book_serial_name = book_serial_name;
        this.isbn = isbn;
        this.description = description;
    }

    public void addAuthors(Author author) {
        this.authors.add(author);
    }
    public void addPublishers(Publisher publisher) {
        this.publishers.add(publisher);
    }
}
