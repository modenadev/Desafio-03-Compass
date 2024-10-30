package io.github.modenadev.userproject.model.dto;

import io.github.modenadev.userproject.model.Address;

public class UserResponseDTO {

    private Long id;
    private String username;
    private String email;
    private String zipCode;
    private Address address; // Campo Address

    public UserResponseDTO(Long id, String username, String email, String zipCode, Address address) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.zipCode = zipCode;
        this.address = address;
    }

    public UserResponseDTO() {} // Construtor sem argumentos

    // Getters e Setters
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

    public String getCep() {
        return zipCode;
    }

    public void setCep(String zipCode) {
        this.zipCode = zipCode;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
