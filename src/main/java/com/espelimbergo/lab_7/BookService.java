package com.espelimbergo.lab_7;

import com.espelimbergo.lab_7.entity.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private final List<Book> books = new ArrayList<>();
    private long nextBookId = 1; // <--- declare it here

    public BookService() {
        books.add(new Book(nextBookId++, "Spring Boot Guide", 300, "John Doe"));
        books.add(new Book(nextBookId++, "GraphQL in Action", 250, "Jane Smith"));
    }

    public List<Book> findAllBooks() {
        return books;
    }

    public Book findById(Long id) {
        return books.stream().filter(b -> b.getId().equals(id)).findFirst().orElse(null);
    }

    public Book addBook(String title, int pages, Long authorId) {
        String authorName = "Author#" + authorId; // temporary mock author
        Book book = new Book(nextBookId++, title, pages, authorName);
        books.add(book);
        return book;
    }

    public Book updateBookTitle(Long id, String newTitle) {
        Book book = findById(id);
        if (book != null) {
            book.setTitle(newTitle);
        }
        return book;
    }

    public boolean deleteBookById(Long id) {
        return books.removeIf(b -> b.getId().equals(id));
    }
}
