package com.example.GCPFirestoreSBTF.entities;

import com.google.cloud.spring.data.firestore.Document;
import com.google.cloud.firestore.annotation.DocumentId;

@Document(collectionName = "books")
public class Book {

    @DocumentId
    private String bookId;

    private String bookName;
    private String bookAuthor;
    private Integer bookSerialNumber;

    @Override
    public String toString() {
        return "Book{" +
                "bookId='" + bookId + '\'' +
                ", bookName='" + bookName + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookSerialNumber=" + bookSerialNumber +
                '}';
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
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
