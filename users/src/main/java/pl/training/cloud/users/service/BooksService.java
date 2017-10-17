package pl.training.cloud.users.service;

import pl.training.cloud.users.model.Book;

import java.util.List;
import java.util.Optional;

public interface BooksService {
    Optional<List<Book>> getBookByUsername(String username);
}
