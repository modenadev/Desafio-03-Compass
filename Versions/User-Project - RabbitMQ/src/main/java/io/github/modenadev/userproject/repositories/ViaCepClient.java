package io.github.modenadev.userproject.repositories;

import io.github.modenadev.userproject.model.dto.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface ViaCepClient {
    @GetMapping("/{cep}/json/")
    AddressResponse searchZipCode(@PathVariable("cep") String cep);
}

