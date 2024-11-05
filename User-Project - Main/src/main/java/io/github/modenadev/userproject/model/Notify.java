package io.github.modenadev.userproject.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Notify {

    private String username;
    private String message;

    public Notify(String username) {
        this.username = username;
    }




}
