package io.github.modenadev.userproject.model.dto;

import java.util.ArrayList;
import java.util.List;

public class UserResponseDTO {

    private Long id;
    private String username;
    private String password;
    private String email;
    private Long cep;
    private List<String> permissions = new ArrayList<>();

    public UserResponseDTO(Long id, String userName,  String password, String email, Long cep) {
        this.id = id;
        this.username = userName;
        this.password = password;
        this.email = email;
        this.cep = cep;
        this.permissions = permissions != null ? permissions : new ArrayList<>();
    }


    public UserResponseDTO() {}

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCep() {
        return cep;
    }

    public void setCep(Long cep) {
        this.cep = cep;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
}
