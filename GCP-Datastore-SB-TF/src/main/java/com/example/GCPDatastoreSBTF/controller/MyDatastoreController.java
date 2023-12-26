package com.example.GCPDatastoreSBTF.controller;


import com.example.GCPDatastoreSBTF.entities.Book;
import com.example.GCPDatastoreSBTF.repo.BookRepo;
import com.google.cloud.datastore.Key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/gcp")
public class MyDatastoreController {

    @Autowired
    BookRepo bookRepo;

    @PostMapping("/save_book")
    public String save_book(@RequestBody Book book) throws Exception {
        try {
            book = bookRepo.save(book);
            return "Book saved successfully! with key:"+book.getKey();
        } catch (Exception e) {
            // Log the error for debugging
            System.out.println(e);
            // Throw a custom exception with a meaningful message for the client
            throw new Exception("Failed to save book: " + e.getMessage());
        }
    }

    @GetMapping("/get_all_books")
    public List<Book> getAllBooks() throws Exception {
        try {
            Iterable<Book> booksIterable = bookRepo.findAll();
            return StreamSupport.stream(booksIterable.spliterator(), false)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println(e);
            throw new Exception("Failed to retrieve books: " + e.getMessage());
        }
    }

    @PutMapping("/update_book/{id}")
    public Book updateBook(@PathVariable String id, @RequestBody Book book) throws Exception {
        try {
            book.setKey(Key.newBuilder("test-project-1-406807",
                    "Book",Long.parseLong(id),"").build()); // Assuming ID is available
            return bookRepo.save(book);
        } catch (Exception e) {
            System.out.println(e);
            throw new Exception("Failed to update book: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete_book/{id}")
    public void deleteBook(@PathVariable String id) throws Exception {
        try {
            bookRepo.deleteById(Key.newBuilder("test-project-1-406807",
                    "Book",Long.parseLong(id),"").build());
        } catch (Exception e) {
            System.out.println(e);
            throw new Exception("Failed to delete book: " + e.getMessage());
        }
    }

}
