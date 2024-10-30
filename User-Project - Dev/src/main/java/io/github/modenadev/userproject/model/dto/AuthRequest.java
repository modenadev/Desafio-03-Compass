package io.github.modenadev.userproject.model.dto;

public class AuthRequest {
    private String userName;
    private String password;

    // Construtor padrão
    public AuthRequest() {}

    // Getters e Setters
    public String getUsername() {
        return userName;
    }

    public void setUsername(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
