package com.example.librarymanagementsystem.controller.dto;

import com.example.librarymanagementsystem.dtos.BookDTO;

import java.util.ArrayList;
import java.util.List;

public class PagedBookDTO {
    private List<BookDTO> bookList = new ArrayList<>();
    private int page;
    private long totalElements;

    public List<BookDTO> getBookList() {
        return bookList;
    }

    public void setBookList(List<BookDTO> bookList) {
        this.bookList = bookList;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }
}
