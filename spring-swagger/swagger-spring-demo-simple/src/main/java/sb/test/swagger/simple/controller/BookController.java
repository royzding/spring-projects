package sb.test.swagger.simple.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import sb.test.swagger.simple.model.Book;
import sb.test.swagger.simple.service.BookService;

@RestController
public class BookController {
	
	private final BookService bookService;
	
	public BookController(BookService bookService) {
		this.bookService=bookService;
	}

    /**
     * POST /book : Add a Book
     *
     * @param book Book to be added in shop (required)
     * @return Book Added (status code 200)
     */
    @ApiOperation(value = "Add a Book", nickname = "addBook", notes = "", response = String.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Book Added", response = String.class) })
    @RequestMapping(value = "/book",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    public ResponseEntity<String> addBook(@ApiParam(value = "Book to be added in shop" ,required=true )  @Valid @RequestBody Book book) {
        return bookService.addBook(book);
    }

    /**
     * GET /book : Get Books
     *
     * @return successful operation (status code 200)
     */
    @ApiOperation(value = "Get Books", nickname = "getBooks", notes = "", response = Book.class, responseContainer = "List", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Book.class, responseContainer = "List") })
    @RequestMapping(value = "/book",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    public ResponseEntity<List<Book>> getBooks() {
        return bookService.getBooks();
    }
}
