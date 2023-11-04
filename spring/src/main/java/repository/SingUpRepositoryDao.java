package repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SingUpRepositoryDao implements SignUpRepository{

    private static Map<Long, User> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public Optional<User> findByeEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByPw(String pw) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return null;
    } 하나도 모르겠다~
}
