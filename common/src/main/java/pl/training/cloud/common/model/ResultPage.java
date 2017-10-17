package pl.training.cloud.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class ResultPage<T> {

    private List<T> content;
    private int pageNumber;
    private int totalPages;

}
