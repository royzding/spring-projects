package sb.test.swagger.simple.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
	@Operation(summary = "Add a Book")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Book Added",  content = @Content) 
    })
    @RequestMapping(value = "/book",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    public ResponseEntity<String> addBook(@Parameter(description = "Book to be added in shop" ,required=true )  @Valid @RequestBody Book book) {
        return bookService.addBook(book);
    }

    /**
     * GET /book : Get Books
     *
     * @return successful operation (status code 200)
     */
    @Operation(summary = "Get Books")
    @ApiResponses(value = { 
    	     @ApiResponse(responseCode = "200", description = "successful operation", content = @Content), 
    	     @ApiResponse(responseCode = "500", description = "internal server error", content = @Content) 
    })
    @RequestMapping(value = "/book",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    public ResponseEntity<List<Book>> getBooks(
    		  @Parameter(name="title(s)", description = "filter: in title(s)")
    	      @RequestParam(required = false) String title,
    	      @RequestParam(defaultValue = "0") int page,
    	      @RequestParam(defaultValue = "3") int size,
    		  @Parameter(name="sort", description = "sort array: such as [id,desc name,asc]")
    	      @RequestParam(defaultValue = "id,desc") String[] sort
    	      ) {
    	
        return bookService.getBooks();
    }
    
    /**
     * GET /book : Get Books
     *
     * @return successful operation (status code 200)
     */
    @Operation(summary = "Get Book by id")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "successful operation", content = 
        	{@Content(mediaType="application/json")}) }
    )
    @RequestMapping(value = "/book/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    public ResponseEntity<Book> getBookById(@PathVariable("id") long id) {
        return bookService.getBookById(id);
    }
}
