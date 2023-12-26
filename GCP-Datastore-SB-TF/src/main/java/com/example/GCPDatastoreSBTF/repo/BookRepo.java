package com.example.GCPDatastoreSBTF.repo;

import com.example.GCPDatastoreSBTF.entities.Book;
import com.google.cloud.spring.data.datastore.repository.DatastoreRepository;
import org.springframework.stereotype.Repository;
import com.google.cloud.datastore.Key;

@Repository
public interface BookRepo extends DatastoreRepository<Book, Key> {

}
