package com.example.GCPCloudSQLPostgreSQLSBTF.entities;

import jakarta.persistence.*;


@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    private String bookName;
    private String bookAuthor;
    private Integer bookSerialNumber;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public Integer getBookSerialNumber() {
        return bookSerialNumber;
    }

    public void setBookSerialNumber(Integer bookSerialNumber) {
        this.bookSerialNumber = bookSerialNumber;
    }
}
