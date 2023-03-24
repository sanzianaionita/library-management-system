package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.controller.dto.PagedBookDTO;
import com.example.librarymanagementsystem.dtos.BookDTO;
import com.example.librarymanagementsystem.enums.Category;
import com.example.librarymanagementsystem.model.BorrowedBooks;
import com.example.librarymanagementsystem.service.BookService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<BookDTO>> getAllBooks() {

        List<BookDTO> bookDTOS = bookService.getAllBooks();
        return ResponseEntity.ok(bookDTOS);
    }

    @PostMapping("")
    public ResponseEntity<BookDTO> addNewBook(@RequestBody BookDTO bookDTO) {

        BookDTO bookCreated = bookService.addNewBook(bookDTO);
        return new ResponseEntity<>(bookCreated, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<BookDTO> editDetailsOfBook(@RequestBody BookDTO bookDTO) {

        BookDTO bookEdited = bookService.editDetailsOfBook(bookDTO);
        return ResponseEntity.ok(bookEdited);
    }

    @GetMapping("")
    public ResponseEntity<PagedBookDTO> getBooks(@RequestParam(required = false) Category category,
                                                  @RequestParam int pageNumber,
                                                  @RequestParam int totalElements) {

        Pageable pageable = PageRequest.of(pageNumber, totalElements);
        PagedBookDTO pagedBook = bookService.getBooks(category, pageable);
        return ResponseEntity.ok(pagedBook);
    }

    @PostMapping("/borrow-a-book")
    public ResponseEntity<BorrowedBooks> borrowBook(@RequestBody BookDTO bookDTO,
                                                    @RequestParam int userId) {

        BorrowedBooks borrowedBook = bookService.borrowBook(bookDTO, userId);
        return ResponseEntity.ok(borrowedBook);
    }

    @PostMapping("/return-a-book")
    public ResponseEntity<BorrowedBooks> returnBook(@RequestBody BookDTO bookDTO,
                                                    @RequestParam int userId) {

        BorrowedBooks returnedBook = bookService.returnBook(bookDTO, userId);
        return ResponseEntity.ok(returnedBook);
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteBook(@RequestParam UUID bookId) {
        bookService.deleteBook(bookId);
        return ResponseEntity.ok().build();
    }
}
