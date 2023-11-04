package repository;

import javax.persistence.Entity;
import java.util.Objects;


public class User {
    private Integer id;
    private String pw;
    private String email;

    public User(Integer id, String pw, String email) {
        this.id = id;
        this.pw = pw;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public String getPw() {
        return pw;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
