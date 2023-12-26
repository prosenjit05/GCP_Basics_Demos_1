package com.example.GCPFirestoreSBTF.controller;

import com.example.GCPFirestoreSBTF.entities.Book;
import com.example.GCPFirestoreSBTF.repo.BookRepo;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/gcp")
public class MyFirestoreController {

    @Autowired
    BookRepo bookRepository;

    @PostMapping("/save_book")
    public String save_book(@RequestBody Book book) {
        try {
            // Inject Firestore instance (best practice)
            Firestore firestore = FirestoreClient.getFirestore();

            // Ensure Document ID is present (for clarity and consistency)
            if (book.getBookId() == null) {
                book.setBookId(UUID.randomUUID().toString()); // Generate unique ID if needed
            }

            // Directly reference the collection by name (explicit and concise)
            CollectionReference booksCollection = firestore.collection("books");

            // Add the book document to the collection
            booksCollection.document(book.getBookId()).set(book);

            return "Book saved successfully!";

        } catch (Exception e) {
            // Handle potential exceptions gracefully
            System.out.println(e);
            return "Failed to save book. Please try again.";
        }
    }

    @GetMapping("/get_all_books")
    public List<Book> get_all_books() {
        Flux<Book> booksFlux = bookRepository.findAll();

        // Block the thread to collect books synchronously
        List<Book> booksList = booksFlux.collectList().block();

        return booksList;
    }

    @PutMapping("/update_book")
    public String update_book(@RequestBody Book book) {
        try {
            // Update the book in Firestore
            bookRepository.save(book).block(); // Block to ensure synchronous completion

            return "Book updated successfully!";

        } catch (Exception e) {
            System.out.println(e);
            return "Failed to update book. Please try again.";
        }
    }

    @DeleteMapping("/delete_book/{bookId}")
    public String delete_book(@PathVariable String bookId) {
        try {
            // Delete the book from Firestore
            bookRepository.deleteById(bookId).block(); // Block to ensure synchronous completion

            return "Book deleted successfully!";

        } catch (Exception e) {
            System.out.println(e);
            return "Failed to delete book. Please try again.";
        }
    }

}
