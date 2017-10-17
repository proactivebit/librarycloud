package pl.training.cloud.users.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.training.cloud.users.model.Book;

import java.util.List;

@FeignClient("books-micro")
public interface FeignBooksClient {
    @RequestMapping(method = RequestMethod.GET, value = "books/username/{username}")
    List<Book> getBookByUsername(@PathVariable("username") String username);
}
