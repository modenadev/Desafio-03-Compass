package io.github.modenadev.userproject.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRequestDTO {


    private String userName;

    private String password;

    private String email;

    private Long cep;

    private String oldPassword;

    private String newPassword;



    // Getters and Setters

    public @NotBlank(message = "Username is required") @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters") String getUserName() {
        return userName;
    }

    public void setUserName(@NotBlank(message = "Username is required") @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters") String userName) {
        this.userName = userName;
    }

    public @NotBlank(message = "Password is required") @Size(min = 6, message = "Password must be at least 6 characters") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password is required") @Size(min = 6, message = "Password must be at least 6 characters") String password) {
        this.password = password;
    }

    public @NotBlank(message = "Email is required") @Size(min = 50, max = 200, message = "Email must be at least 100 characters") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email is required") @Size(min = 50, max = 200, message = "Email must be at least 100 characters") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Cep is required") @Size(min = 8, max = 12, message = "Email must be at least 8 characters") Long getCep() {
        return cep;
    }

    public void setCep(@NotBlank(message = "Cep is required") @Size(min = 8, max = 12, message = "Email must be at least 8 characters") Long cep) {
        this.cep = cep;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
