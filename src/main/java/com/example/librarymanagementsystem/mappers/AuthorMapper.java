package com.example.librarymanagementsystem.mappers;

import com.example.librarymanagementsystem.dtos.AuthorDTO;
import com.example.librarymanagementsystem.model.Author;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    Author toEntity(AuthorDTO authorDTO);

    AuthorDTO toDto(Author author);

    List<Author> toEntity(List<AuthorDTO> authorDTOs);

    List<AuthorDTO> toDto(List<Author> authors);
}
