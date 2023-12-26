package com.example.GCPDatastoreSBTF.entities;

import com.google.cloud.datastore.Key;

import com.google.cloud.spring.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;

@Entity(name = "Book")
public class Book {

    @Id
    private Key key;

    private String bookName;
    private String bookAuthor;
    private Integer bookSerialNumber;

    @Override
    public String toString() {
        return "Book{" +
                "key=" + key +
                ", bookName='" + bookName + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookSerialNumber=" + bookSerialNumber +
                '}';
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
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
