package com.example.GCPCloudSQLPostgreSQLSBTF.controller;

import com.example.GCPCloudSQLPostgreSQLSBTF.entities.Book;
import com.example.GCPCloudSQLPostgreSQLSBTF.repos.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/gcp")
public class CloudSQLController {

    @Autowired
    BookRepository bookRepository;

    @PostMapping("/save_book")
    public String save_book(@RequestBody Book book) {
        Book savedBook = bookRepository.save(book);
        return "Book Saved Successfully with ID:"+savedBook.getBookId();
    }

    @GetMapping("/get_all_books")
    public List<Book> get_all_books() {
        List<Book> savedBooks = bookRepository.findAll();
        return savedBooks;
    }

    @PutMapping("/update_book")
    public Book update_book(@RequestBody Book book){
        Book savedBook = bookRepository.save(book);
        return savedBook;
    }

    @DeleteMapping("/delete_book_by_bookid")
    public String delete_book_by_bookid(@RequestParam String bookId){
        bookRepository.deleteById(Long.valueOf(bookId));
        return "Book Deleted Successfully with Book ID:"+bookId;
    }

}
