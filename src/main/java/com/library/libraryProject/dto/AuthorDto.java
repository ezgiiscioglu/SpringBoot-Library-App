package com.library.libraryProject.dto;

import com.library.libraryProject.model.Book;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDto {
    private Long author_id;
    @NotNull
    private String author_name;
    @NotNull
    private String description;
    private Book book;
}
