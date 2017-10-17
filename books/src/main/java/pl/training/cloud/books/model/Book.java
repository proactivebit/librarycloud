package pl.training.cloud.books.model;

import lombok.Data;

import javax.persistence.*;

@Table(name = "books")
@Entity
@Data
public class Book {

    @GeneratedValue
    @Id
    private Long id;
    private String title;
    private String authors;
    private String category;
    private boolean favourite;
    private int rating;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    private String supportEmail;
    private String username;

}
