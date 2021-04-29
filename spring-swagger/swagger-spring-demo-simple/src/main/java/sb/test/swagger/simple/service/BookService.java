package sb.test.swagger.simple.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import sb.test.swagger.simple.model.Book;

public interface BookService {
    ResponseEntity<String> addBook(Book book);
    ResponseEntity<List<Book>> getBooks();
}
