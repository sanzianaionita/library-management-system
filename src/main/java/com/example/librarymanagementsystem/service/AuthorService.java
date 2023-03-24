package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.dtos.AuthorDTO;
import com.example.librarymanagementsystem.mappers.AuthorMapper;
import com.example.librarymanagementsystem.model.Author;
import com.example.librarymanagementsystem.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorMapper authorMapper;
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorMapper authorMapper,
                         AuthorRepository authorRepository) {
        this.authorMapper = authorMapper;
        this.authorRepository = authorRepository;
    }

    public List<AuthorDTO> getAllAuthors() {
        return authorMapper.toDto(authorRepository.findAll());
    }

    public AuthorDTO addNewAuthor(AuthorDTO authorDTO) {

        Optional<Author> firstByFirstNameAndLastName = authorRepository.findFirstByFirstNameAndLastName(authorDTO.getFirstName(), authorDTO.getLastName());

        if (firstByFirstNameAndLastName.isPresent()) {
            throw new RuntimeException("This author already exists!");
        }

        Author author = authorMapper.toEntity(authorDTO);

        return authorMapper.toDto(authorRepository.save(author));
    }
}
