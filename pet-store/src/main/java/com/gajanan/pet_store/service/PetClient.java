package com.gajanan.pet_store.service;

import com.gajanan.pet_store.model.Pet;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;

import java.util.List;
import java.util.Optional;

public interface PetClient {
    @GetExchange("/pets")
    public List<Pet> findAll();

    @GetExchange("/pets/{petId}")
    public Optional<Pet> findById(@PathVariable int petId);

    @PostExchange("/pets")
    public void save(@RequestBody Pet pet);

    @PutExchange("/pets/{petId}")
    public void update(@RequestBody Pet pet, @PathVariable int petId);

    @DeleteExchange("/pets/{petId}")
    public void delete(@PathVariable int petId);
}
