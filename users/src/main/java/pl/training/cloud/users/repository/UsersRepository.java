package pl.training.cloud.users.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.training.cloud.users.model.User;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {

    Optional<User> getByLogin(String login);

}
