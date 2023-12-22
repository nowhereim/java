package com.example.JpaMigrationLab.book.repository;

import com.example.JpaMigrationLab.book.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
