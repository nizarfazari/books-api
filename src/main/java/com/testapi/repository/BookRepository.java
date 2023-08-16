package com.testapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testapi.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
