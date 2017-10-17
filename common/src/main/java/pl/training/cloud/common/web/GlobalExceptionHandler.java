package pl.training.cloud.common.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.training.cloud.common.model.Mapper;

import java.util.Locale;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class GlobalExceptionHandler {

    private Mapper mapper;

    @Autowired
    public GlobalExceptionHandler(Mapper mapper) {
        this.mapper = mapper;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity onException(Exception ex, Locale locale) {
        ex.printStackTrace();
        return new ResponseEntity<>(mapper.map(ex, locale), INTERNAL_SERVER_ERROR);
    }

}
