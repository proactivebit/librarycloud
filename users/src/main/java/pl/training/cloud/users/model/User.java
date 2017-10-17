package pl.training.cloud.users.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Entity
@Data
public class User {

    @GeneratedValue
    @Id
    private Long id;
    @NonNull
    @Column(unique = true)
    private String login;
    @NonNull
    @Column(nullable = false)
    private String password;
    private boolean active;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Authority> authorities;

    public void addAuthority(Authority authority) {
        if (authorities == null) {
            authorities = new HashSet<>();
        }
        authorities.add(authority);
    }

}
