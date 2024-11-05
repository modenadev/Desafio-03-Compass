package io.github.modenadev.userproject.model.dto;

import jakarta.validation.constraints.NotBlank;

public class AddressRequest {

    @NotBlank(message = "Zip code is required")
    private String zipCode;


    public AddressRequest() {}

    public AddressRequest(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "AddressRequest{" +
                "zipCode='" + zipCode + '\'' +
                '}';
    }
}
