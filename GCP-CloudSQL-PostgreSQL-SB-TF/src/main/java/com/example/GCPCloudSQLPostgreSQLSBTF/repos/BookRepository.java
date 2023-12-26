package com.example.GCPCloudSQLPostgreSQLSBTF.repos;

import com.example.GCPCloudSQLPostgreSQLSBTF.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
