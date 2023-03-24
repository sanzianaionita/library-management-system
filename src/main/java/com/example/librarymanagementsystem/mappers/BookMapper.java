package com.example.librarymanagementsystem.mappers;

import com.example.librarymanagementsystem.dtos.BookDTO;
import com.example.librarymanagementsystem.model.Book;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    Book toEntity(BookDTO bookDTO);

    BookDTO toDTO(Book book);

    List<Book> toEntity(List<BookDTO> bookDTOs);

    List<BookDTO> toDTO(List<Book> books);
}
