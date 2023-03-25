package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.controller.dto.PagedBookDTO;
import com.example.librarymanagementsystem.customExceptions.CustomException;
import com.example.librarymanagementsystem.dtos.BookDTO;
import com.example.librarymanagementsystem.enums.Category;
import com.example.librarymanagementsystem.mappers.BookMapper;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.model.BorrowedBooks;
import com.example.librarymanagementsystem.repository.AuthorRepository;
import com.example.librarymanagementsystem.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {

    private final BookMapper bookMapper;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BorrowedBooksService borrowedBooksService;

    public BookService(BookMapper bookMapper,
                       BookRepository bookRepository,
                       AuthorRepository authorRepository, BorrowedBooksService borrowedBooksService) {
        this.bookMapper = bookMapper;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.borrowedBooksService = borrowedBooksService;
    }

    public List<BookDTO> getAllBooks() {
        return bookMapper.toDTO(bookRepository.findAll());
    }

    public BookDTO addNewBook(BookDTO bookDTO) {
        if (bookDTO.getAuthor() == null) {
            throw new CustomException("Author is not provided", HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value());
        }

        if (authorRepository.findById(bookDTO.getAuthor().getId()).isEmpty()) {
            throw new CustomException("The author provided does not exist!", HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value());
        }

        Optional<Book> foundBook = bookRepository.findFirstByTitleAndAuthorId(bookDTO.getTitle(), bookDTO.getAuthor().getId());

        if (foundBook.isPresent()) {
            throw new CustomException("This book already exists!", HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value());
        }

        Book book = bookMapper.toEntity(bookDTO);
        return bookMapper.toDTO(bookRepository.save(book));
    }

    public BookDTO editDetailsOfBook(BookDTO bookDTO) {

        Optional<Book> byId = bookRepository.findById(bookDTO.getId());

        if (byId.isEmpty()) {
            throw new CustomException("The book provided does not exist!", HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value());
        }

        Book book = bookMapper.toEntity(bookDTO);
        return bookMapper.toDTO(bookRepository.save(book));
    }

    public PagedBookDTO getBooks(Category category, Pageable pageable) {

        Page<Book> books;
        if (category != null) {
            books = bookRepository.findBookByCategoryOrderByTitle(category, pageable);
        } else {
            books = bookRepository.findAll(pageable);
        }

        PagedBookDTO pagedBookDTO = new PagedBookDTO();

        pagedBookDTO.setPage(pageable.getPageNumber());
        pagedBookDTO.setTotalElements(books.getTotalElements());
        pagedBookDTO.setBookList(bookMapper.toDTO(books.getContent()));

        return pagedBookDTO;
    }

    public BorrowedBooks borrowBook(BookDTO bookDTO, int userId) {
        Optional<Book> byId = bookRepository.findById(bookDTO.getId());

        if (byId.isEmpty()) {
            throw new CustomException("The book provided does not exist!", HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value());
        }

        Book book = byId.get();

        if (book.getAvailableNumberOfCopies() <= 0) {
            throw new CustomException("The book provided does not have an available copy", HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value());
        }

        return borrowedBooksService.borrowBook(book, userId);
    }

    public BorrowedBooks returnBook(BookDTO bookDTO, int userId) {
        Optional<Book> byId = bookRepository.findById(bookDTO.getId());

        if (byId.isEmpty()) {
            throw new CustomException("The book provided does not exist!", HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value());
        }

        Book book = byId.get();

        return borrowedBooksService.returnBook(book, userId);
    }

    public void deleteBook(UUID bookId) {

        Optional<Book> byId = bookRepository.findById(bookId);
        if (byId.isEmpty()) {
            throw new CustomException("Book does not exist!", HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value());
        }

        Book book = byId.get();
        book.setDeleted(true);

        bookRepository.save(book);
    }
}
