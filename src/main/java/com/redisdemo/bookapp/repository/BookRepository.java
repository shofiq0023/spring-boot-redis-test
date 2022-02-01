package com.redisdemo.bookapp.repository;

import com.redisdemo.bookapp.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
