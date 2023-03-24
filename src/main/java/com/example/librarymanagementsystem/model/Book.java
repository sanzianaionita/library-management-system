package com.example.librarymanagementsystem.model;

import com.example.librarymanagementsystem.enums.Category;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "BOOK")
public class Book {

    @Id
    @GenericGenerator(name = "generator", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "generator")
    @Column(name = "ID", unique = true, nullable = false)
    private UUID id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CATEGORY")
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "DELETED", nullable = false)
    private boolean isDeleted;

    @Column(name = "YEAR")
    private int year;

    @Column(name = "TOTAL_NUMBER_OF_COPIES")
    private int totalNumberOfCopies;

    @Column(name = "AVAILABLE_NUMBER_OF_COPIES")
    private int availableNumberOfCopies;

    @JoinColumn(name = "AUTHOR_ID")
    @ManyToOne
    private Author author;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTotalNumberOfCopies() {
        return totalNumberOfCopies;
    }

    public void setTotalNumberOfCopies(int totalNumberOfCopies) {
        this.totalNumberOfCopies = totalNumberOfCopies;
    }

    public int getAvailableNumberOfCopies() {
        return availableNumberOfCopies;
    }

    public void setAvailableNumberOfCopies(int availableNumberOfCopies) {
        this.availableNumberOfCopies = availableNumberOfCopies;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category=" + category +
                ", year=" + year +
                ", totalNumberOfCopies=" + totalNumberOfCopies +
                ", availableNumberOfCopies=" + availableNumberOfCopies +
                ", author=" + author +
                '}';
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
