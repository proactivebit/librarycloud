package pl.training.cloud.users.service;

import feign.FeignException;
import org.springframework.stereotype.Service;
import pl.training.cloud.users.model.Book;

import java.util.List;
import java.util.Optional;

@Service
public class FeignBooksService implements BooksService {

    private FeignBooksClient feignBooksClient;

    public FeignBooksService(FeignBooksClient feignBooksClient) {
        this.feignBooksClient = feignBooksClient;
    }


    @Override
    public Optional<List<Book>> getBookByUsername(String username) {
        try {
            List<Book> book = feignBooksClient.getBookByUsername(username);
            return book != null ? Optional.of(book) : Optional.empty();
        } catch (FeignException ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }

}
