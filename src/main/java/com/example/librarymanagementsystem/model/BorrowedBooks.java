package com.example.librarymanagementsystem.model;

import com.example.librarymanagementsystem.enums.BorrowedBooksStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "BORROWED_BOOKS")
public class BorrowedBooks {

    @Id
    @GenericGenerator(name = "generator", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "generator")
    @Column(name = "ID", unique = true, nullable = false)
    private UUID id;

    @Column(name = "USER_ID")
    private int userId;

    @Column(name = "STATUS")
    private BorrowedBooksStatus status;

    @OneToOne
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "BorrowedBooks{" +
                "id=" + id +
                ", userId=" + userId +
                ", status=" + status +
                ", book=" + book +
                '}';
    }
}
