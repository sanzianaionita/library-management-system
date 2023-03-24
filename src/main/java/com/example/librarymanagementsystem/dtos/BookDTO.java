package com.example.librarymanagementsystem.dtos;

import com.example.librarymanagementsystem.enums.Category;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class BookDTO {

    private UUID id;

    @NotNull
    @NotBlank
    private String title;

    @NotNull
    @NotBlank
    private Category category;

    private boolean isDeleted;

    @Min(1)
    private int year;

    @Min(0)
    private int totalNumberOfCopies;

    @Min(0)
    private int availableNumberOfCopies;
    private AuthorDTO author;

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

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
