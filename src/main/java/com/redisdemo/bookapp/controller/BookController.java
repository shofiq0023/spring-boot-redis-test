package com.redisdemo.bookapp.controller;

import com.redisdemo.bookapp.model.Book;
import com.redisdemo.bookapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService service;

    @PostMapping("/add")
    public void addBook(@RequestBody Book book) {
        service.addBook(book);
    }

    @GetMapping("/get/{id}")
    public Optional<Book> getBook(@PathVariable("id") Long id) {
        return service.getBook(id);
    }

    @PutMapping("/update/{id}")
    public Book updateBook(@PathVariable("id") Long id, @RequestBody Book book) {
        return service.updateBook(id, book);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBook(@PathVariable("id") Long id) {
        service.deleteBook(id);
    }
}
