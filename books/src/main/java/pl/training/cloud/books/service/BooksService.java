package pl.training.cloud.books.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.training.cloud.books.repository.BooksRepository;
import pl.training.cloud.books.model.Book;
import pl.training.cloud.common.model.ResultPage;

import java.util.List;

@Service
public class BooksService {

    private BooksRepository booksRepository;

    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public Book addBook(Book book) {
        booksRepository.saveAndFlush(book);
        return book;
    }

    public ResultPage<Book> getBooks(int pageNumber, int pageSize) {
        Page<Book> bookPage = booksRepository.findAll(new PageRequest(pageNumber, pageSize));
        return new ResultPage<>(bookPage.getContent(), bookPage.getNumber(), bookPage.getTotalPages());
    }

    public Book getBookById(Long id) {
        return booksRepository.getById(id)
                .orElseThrow(BookNotFoundException::new);
    }

    public List<Book> getBookByUsername(String username) {
        return booksRepository.getByUsername(username)
                .orElseThrow(BookNotFoundException::new);
    }

    public void updateBook(Book book) {
        getBookById(book.getId());
        booksRepository.saveAndFlush(book);
    }

}
