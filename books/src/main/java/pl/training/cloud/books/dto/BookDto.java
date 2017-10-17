package pl.training.cloud.books.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(value = "User")
@Data
public class BookDto {

    private Long id;
    private String title;
    private String authors;
    private String category;
    private boolean favourite;
    private int rating;
    private GenreDto genre;
    private String supportEmail;

}
