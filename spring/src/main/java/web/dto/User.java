package web.dto;

import java.util.Objects;

public class User {
    private String pw;
    private String user;

    public User(String pw, String user) {
        this.pw = pw;
        this.user = user;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "User{" +
                "pw='" + pw + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
