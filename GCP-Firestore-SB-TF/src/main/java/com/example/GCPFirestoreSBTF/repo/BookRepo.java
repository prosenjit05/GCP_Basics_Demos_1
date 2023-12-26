package com.example.GCPFirestoreSBTF.repo;

import com.google.cloud.spring.data.firestore.FirestoreReactiveRepository;
import com.example.GCPFirestoreSBTF.entities.Book;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends FirestoreReactiveRepository<Book> {

}
