package com.example.librarymanagementsystem.repository;

import com.example.librarymanagementsystem.enums.Category;
import com.example.librarymanagementsystem.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {

    Optional<Book> findFirstByTitleAndAuthorId(String title, UUID authorId);

    Page<Book> findBookByCategoryOrderByTitle(Category category, Pageable pageable);
}
