package com.library.libraryProject.dto;

import com.library.libraryProject.model.Book;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherDto {
    private Long publisher_id;
    @NotNull
    private String publisher_name;
    @NotNull
    private String description;
    private Book book;
}
