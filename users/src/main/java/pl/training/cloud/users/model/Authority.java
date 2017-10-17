package pl.training.cloud.users.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(exclude = "users")
@NoArgsConstructor
@Table(name = "authorities")
@Entity
@Data
public class Authority {

    private static final String ROLE_PREFIX = "ROLE_";

    @GeneratedValue
    @Id
    private Long id;
    @NonNull
    private String name;
    @ManyToMany(mappedBy = "authorities")
    private Set<User> users = new HashSet<>();

    public Authority(String name) {
        this.name = ROLE_PREFIX + name;
    }

}
