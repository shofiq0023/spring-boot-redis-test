package com.redisdemo.bookapp.service;

import com.redisdemo.bookapp.model.Book;
import com.redisdemo.bookapp.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {
    private final Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookRepository repo;

    public void addBook(Book book) {
        logger.info("[POST] - Adding book to DB");
        repo.save(book);
    }

    @Cacheable(cacheNames = "books", key = "#id")
    public Optional<Book> getBook(Long id) {
        logger.info("[GET] - Getting book from DB");
        return repo.findById(id);
    }

    @CachePut(cacheNames = "books", key = "#id")
    public Book updateBook(Long id, Book book) {
        if(repo.existsById(id)) {
            repo.save(book);
            logger.info("[PUT] - Updating book in DB");
            return book;
        } else {
            return null;
        }
    }

    @CacheEvict(cacheNames = "books", key = "#id")
    public void deleteBook(Long id) {
        logger.info("[DELETE] - Deleting book from DB");
        repo.deleteById(id);
    }
}
