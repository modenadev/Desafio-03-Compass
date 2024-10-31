package io.github.modenadev.userproject.services;

import io.github.modenadev.userproject.model.dto.AddressRequest;
import io.github.modenadev.userproject.model.dto.AddressResponse;
import io.github.modenadev.userproject.repositories.ViaCepClient;
import org.springframework.stereotype.Service;

@Service
public class AddressServices {

    private final ViaCepClient viaCEP;

    public AddressServices(ViaCepClient viaCEP) {
        this.viaCEP = viaCEP;
    }

    public AddressResponse execute(AddressRequest request) {
        return viaCEP.searchZipCode(request.getZipCode());
    }
}
