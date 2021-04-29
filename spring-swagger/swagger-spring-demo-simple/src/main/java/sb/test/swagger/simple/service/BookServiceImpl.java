package sb.test.swagger.simple.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;

import sb.test.swagger.simple.model.Book;

@Service
public class BookServiceImpl implements BookService {

    public Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

     public ResponseEntity<String> addBook(Book book) {
        return ResponseEntity.ok(book.getTitle()+" is added");
    }


    public ResponseEntity<List<Book>> getBooks() {
        List<Book> bookList = new ArrayList<>();
        Book a = new Book();
        a.setAuthor("Patrick");
        a.setTitle("Name of the Wind");
        Book b = new Book();
        b.setAuthor("Brandon");
        b.setTitle("Name of the Water");
        bookList.add(a);
        bookList.add(b);
        return ResponseEntity.ok(bookList);

    }
    
}
