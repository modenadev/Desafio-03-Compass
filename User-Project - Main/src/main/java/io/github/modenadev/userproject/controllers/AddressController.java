package io.github.modenadev.userproject.controllers;


import io.github.modenadev.userproject.model.dto.AddressRequest;
import io.github.modenadev.userproject.services.AddressServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressServices addressServices;


    public AddressController(AddressServices addressServices) {
        this.addressServices = addressServices;
    }

    @GetMapping("/consult")
    public ResponseEntity consultZipCode(@RequestBody AddressRequest addressRequest) {
        return ResponseEntity.ok(addressServices.execute(addressRequest));
    }
}
