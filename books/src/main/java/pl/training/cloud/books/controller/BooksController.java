package pl.training.cloud.books.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.training.cloud.books.dto.BookDto;
import pl.training.cloud.books.model.Book;
import pl.training.cloud.books.service.BooksService;
import pl.training.cloud.common.dto.PageDto;
import pl.training.cloud.common.model.Mapper;
import pl.training.cloud.common.web.UriBuilder;
import pl.training.cloud.common.model.ResultPage;

import java.net.URI;
import java.util.List;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.noContent;

@Api(description = "Books resource")
@RequestMapping(value = "books")
@RestController
public class BooksController {

    private BooksService booksService;
    private Mapper mapper;
    private UriBuilder uriBuilder = new UriBuilder();

    public BooksController(BooksService booksService, Mapper mapper) {
        this.booksService = booksService;
        this.mapper = mapper;
    }

    @ApiOperation(value = "Add new book")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createUser(@ApiParam(name = "book") @RequestBody BookDto bookDto) {
        Book book = mapper.map(bookDto, Book.class);
        booksService.addBook(book);
        URI uri = uriBuilder.requestUriWithId(book.getId());
        return created(uri).body(book);
    }

    @ApiOperation(value = "Get books", response = PageDto.class)
    @RequestMapping(method = RequestMethod.GET)
    public PageDto<BookDto> getBooks(
            @RequestParam(required = false, defaultValue = "0", name = "pageNumber") int pageNumber,
            @RequestParam(required = false, defaultValue = "10", name = "pageSize") int pageSize) {
        ResultPage<Book> resultPage = booksService.getBooks(pageNumber, pageSize);
        List<BookDto> booksDtos = mapper.map(resultPage.getContent(), BookDto.class);
        return new PageDto<>(booksDtos, resultPage.getPageNumber(), resultPage.getTotalPages());
    }

    @ApiOperation(value = "Get book by id", response = BookDto.class)
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public BookDto getBookById(@PathVariable Long id) {
        Book book = booksService.getBookById(id);
        return mapper.map(book, BookDto.class);
    }

    @ApiOperation(value = "Get book by username", response = BookDto.class)
    @RequestMapping(value = "/username/{username}", method = RequestMethod.GET)
    public List<BookDto> getBookById(@PathVariable String username) {
        List<Book> books = booksService.getBookByUsername(username);
        return mapper.map(books, BookDto.class);
    }

    @ApiOperation(value = "Update book")
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity updateBook(@ApiParam(name = "book") @RequestBody BookDto bookDto, @PathVariable Long id) {
        Book book = mapper.map(bookDto, Book.class);
        book.setId(id);
        booksService.updateBook(book);
        return noContent().build();
    }

}
