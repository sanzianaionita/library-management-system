package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.enums.BorrowedBooksStatus;
import com.example.librarymanagementsystem.mappers.BorrowedBooksMapper;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.model.BorrowedBooks;
import com.example.librarymanagementsystem.repository.BookRepository;
import com.example.librarymanagementsystem.repository.BorrowedBooksRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowedBooksService {

    private final BorrowedBooksMapper borrowedBooksMapper;
    private final BorrowedBooksRepository borrowedBooksRepository;
    private final BookRepository bookRepository;

    public BorrowedBooksService(BorrowedBooksMapper borrowedBooksMapper,
                                BorrowedBooksRepository borrowedBooksRepository,
                                BookRepository bookRepository) {

        this.borrowedBooksMapper = borrowedBooksMapper;
        this.borrowedBooksRepository = borrowedBooksRepository;
        this.bookRepository = bookRepository;
    }

    public BorrowedBooks borrowBook(Book book, int userId) {

        BorrowedBooks borrowedBooks = new BorrowedBooks();

        borrowedBooks.setUserId(userId);
        borrowedBooks.setStatus(BorrowedBooksStatus.BORROWED);
        borrowedBooks.setBook(book);

        BorrowedBooks save = borrowedBooksRepository.save(borrowedBooks);

        book.setAvailableNumberOfCopies(book.getAvailableNumberOfCopies() - 1);
        bookRepository.save(book);
        return save;
    }

    public BorrowedBooks returnBook(Book book, int userId) {

        List<BorrowedBooks> borrowedBooks = borrowedBooksRepository.findAllByBookId(book.getId());

        if (borrowedBooks.isEmpty()) {
            throw new RuntimeException("The book provided does not exist!");
        }

        boolean anyMatch = borrowedBooks.stream()
                .anyMatch(borrowedBook -> borrowedBook.getUserId() == userId);

        if (!anyMatch) {
            throw new RuntimeException("The user did not borrow the provided book!");
        }
        BorrowedBooks foundBorrowedBook = borrowedBooks.stream()
                .filter(bb -> bb.getUserId() == userId)
                .findFirst().orElse(null);

        foundBorrowedBook.setStatus(BorrowedBooksStatus.RETURNED);

        book.setAvailableNumberOfCopies(book.getAvailableNumberOfCopies() + 1);
        bookRepository.save(book);

        return borrowedBooksRepository.save(foundBorrowedBook);
    }
}
