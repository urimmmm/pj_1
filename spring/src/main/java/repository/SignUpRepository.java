package repository;

import java.util.List;
import java.util.Optional;

public interface SignUpRepository {
    User save(User user);
    Optional<User> findByeEmail(String email);
    Optional<User> findByPw(String pw);
    List<User> findAll();

}
