package pl.training.cloud.users.model;

import lombok.Data;


@Data
public class Book {

    private Long id;
    private String title;
    private String authors;
    private String category;
    private boolean favourite;
    private int rating;
    private String supportEmail;
}
