package com.library.libraryProject.model;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "publishers")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long publisher_id;

    @Column(length = 50)
    private String publisher_name;

    @Column(length = 1000)
    private String description;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "publisher")
    private List<Book> books;

    public Publisher(String publisher_name, String description) {
        this.publisher_name = publisher_name;
        this.description = description;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }
}
