package com.example.GCPpubsubSBTFProducer.dto;

import java.io.Serializable;

public class MyTopicDto implements Serializable {

    private Integer bookId;
    private String bookName;
    private String bookAuthor;
    private Integer bookSerialNumber;

    @Override
    public String toString() {
        return "MyTopicDto{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookSerialNumber=" + bookSerialNumber +
                '}';
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
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
