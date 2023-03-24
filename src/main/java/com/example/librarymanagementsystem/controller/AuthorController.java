package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.dtos.AuthorDTO;
import com.example.librarymanagementsystem.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {

        List<AuthorDTO> authorDTOS = authorService.getAllAuthors();
        return ResponseEntity.ok(authorDTOS);
    }

    @PostMapping("")
    public ResponseEntity<AuthorDTO> addNewAuthor(@RequestBody AuthorDTO authorDTO) {

        AuthorDTO authorCreated = authorService.addNewAuthor(authorDTO);
        return new ResponseEntity<>(authorCreated, HttpStatus.CREATED);
    }
}
