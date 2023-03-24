package com.example.librarymanagementsystem.mappers;

import com.example.librarymanagementsystem.dtos.BorrowedBooksDTO;
import com.example.librarymanagementsystem.model.BorrowedBooks;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BorrowedBooksMapper {

    BorrowedBooks toEntity(BorrowedBooksDTO borrowedBooksDTO);

    BorrowedBooksDTO toDTO(BorrowedBooks borrowedBooks);

    List<BorrowedBooks> toEntity(List<BorrowedBooksDTO> borrowedBooksDTOs);

    List<BorrowedBooksDTO> toDTO(List<BorrowedBooks> borrowedBooks);
}
