package com.library.libraryProject.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long book_id;

    @NotNull
    @Column(length = 100)
    private String book_name;

    @Column(length = 100)
    private String book_sub_name;

    @NotNull
    @Column(length = 100)
    private String book_serial_name;

    @Column(unique = true)
    private String isbn;

    @NotNull
    @Column( length = 1000)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    public Book(String book_name, String book_sub_name, String book_serial_name, String isbn, String description) {
        this.book_name = book_name;
        this.book_sub_name = book_sub_name;
        this.book_serial_name = book_serial_name;
        this.isbn = isbn;
        this.description = description;
    }
}
