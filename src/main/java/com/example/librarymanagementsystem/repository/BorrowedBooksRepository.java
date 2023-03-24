package com.example.librarymanagementsystem.repository;

import com.example.librarymanagementsystem.model.BorrowedBooks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BorrowedBooksRepository extends JpaRepository<BorrowedBooks, UUID> {
    List<BorrowedBooks> findAllByBookId(UUID bookId);
}
