package com.example.librarymanagementsystem.dtos;

import com.example.librarymanagementsystem.enums.BorrowedBooksStatus;
import com.example.librarymanagementsystem.model.Book;

import java.util.UUID;

public class BorrowedBooksDTO {

    private UUID id;
    private int user_id;
    private BorrowedBooksStatus status;
    private Book book;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public BorrowedBooksStatus getStatus() {
        return status;
    }

    public void setStatus(BorrowedBooksStatus status) {
        this.status = status;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
