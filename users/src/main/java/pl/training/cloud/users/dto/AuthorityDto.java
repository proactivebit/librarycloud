package pl.training.cloud.users.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(value = "Authority")
@Data
public class AuthorityDto {

    private String name;

}
