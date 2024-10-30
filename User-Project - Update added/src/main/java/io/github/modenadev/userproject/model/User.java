package io.github.modenadev.userproject.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User implements  Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="user_name", unique = true, nullable = false)
    private String userName;

    @Column(name ="full_name", unique = true, nullable = false)
    private String fullName;

    @Column(name ="password")
    private String password;

    @Column(name ="email", nullable = false)
    private String email;

    @Column(name ="cep", nullable = false)
    private Long cep;


    public User() {}


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(userName, user.userName) && Objects.equals(fullName, user.fullName) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && Objects.equals(cep, user.cep);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, fullName, password, email, cep);
    }
}


