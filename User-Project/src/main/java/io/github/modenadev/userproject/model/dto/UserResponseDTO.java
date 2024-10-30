package io.github.modenadev.userproject.model.dto;

public class UserResponseDTO {

    private Long id;
    private String userName;
    private String fullName;
    private String password;
    private String email;
    private Long cep;

    public UserResponseDTO(Long id, String userName, String fullName, String password, String email, Long cep) {
        this.id = id;
        this.userName = userName;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.cep = cep;

    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

}
