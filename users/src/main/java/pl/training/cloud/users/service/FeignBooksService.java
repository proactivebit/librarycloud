package pl.training.cloud.users.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import feign.FeignException;
import org.springframework.stereotype.Service;
import pl.training.cloud.users.model.Book;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class FeignBooksService implements BooksService {

    private FeignBooksClient feignBooksClient;

    public FeignBooksService(FeignBooksClient feignBooksClient) {
        this.feignBooksClient = feignBooksClient;
    }



    @HystrixCommand(
            threadPoolKey = "booksThreadPool",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize",value="10"),
                    @HystrixProperty(name="maxQueueSize", value="15")
            },
            commandProperties = {
                    @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="10"),
                    @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="10"),
                    @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="20000"),
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "12000")
            }
    )
    @Override
    public Optional<List<Book>> getBookByUsername(String username) {
        randomDelay();
        try {
            List<Book> book = feignBooksClient.getBookByUsername(username);
            return book != null ? Optional.of(book) : Optional.empty();
        } catch (FeignException ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    private void randomDelay() {
        Random random = new Random();
        if (random.nextInt(3) == 2) {
            try {
                Thread.sleep(random.nextInt(11_000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
