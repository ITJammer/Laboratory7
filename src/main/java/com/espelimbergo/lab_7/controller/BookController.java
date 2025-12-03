package com.espelimbergo.lab_7.controller;

import com.espelimbergo.lab_7.service.BookService;
import com.espelimbergo.lab_7.dto.NewBookDto;
import com.espelimbergo.lab_7.entity.Author;
import com.espelimbergo.lab_7.entity.Book;
import com.espelimbergo.lab_7.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;


// http://localhost:8080/graphql
@Controller
@AllArgsConstructor
public class BookController {
    private final BookService service;
    private final BookRepository bookRepository;

    @QueryMapping
    public List<Book> allBooks() {
        return service.findAllBooks();
    }

    @QueryMapping
    public Book bookById(@Argument Long id) {
        return service.findById(id);
    }

    @MutationMapping("addBookUsingInputType")
    public Book addBook(@Argument NewBookDto newBook) {
        return service.addBook(newBook);
    }

    @MutationMapping
    public Book updateBook(
            @Argument Long bookId,
            @Argument NewBookDto updatedBook
    ) {
        service.updateBook(bookId, updatedBook);
        return service.findById(bookId);
    }

    @MutationMapping
    public String deleteBook(@Argument @NonNull Long id) {
        return service.deleteBookById(id) ? "Book removed" : "Failed to remove book";
    }

    @QueryMapping
    public List<Book> findByAuthor(@Argument AuthorDto author) {
        var a = new Author(author.id(), author.name(), new ArrayList<>());
        return bookRepository.findByAuthor(a);
    }

    record AuthorDto (Long id, String name){}
}