package com.library.libraryProject.dto;

import com.library.libraryProject.model.Author;
import com.library.libraryProject.model.Publisher;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private Long book_id;
    @NotNull
    private String book_name;
    private String book_serial_name;
    private String book_sub_name;
    @NotNull
    private String isbn;
    private String description;
    //private Author author;
    //private Publisher publisher;
}
