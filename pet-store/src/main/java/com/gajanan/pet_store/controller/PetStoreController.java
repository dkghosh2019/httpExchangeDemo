package com.gajanan.pet_store.controller;

import com.gajanan.pet_store.model.Pet;
import com.gajanan.pet_store.service.PetClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/petstore")
public class PetStoreController {

    private final PetClient petClient;

    public PetStoreController(PetClient petClient) {
        this.petClient = petClient;
    }


    @GetMapping("/pets")
    public List<Pet> findAll(){
            return petClient.findAll();
    }

    @GetMapping("/pets/{id}")
    public Optional<Pet> findById(@PathVariable int id){
        return petClient.findById(id);
    }

    @PostMapping("/pets")
    public void save(@RequestBody Pet pet){
        petClient.save(pet);
    }

    @PutMapping("/pets/{id}")
    public void update(@RequestBody Pet pet, @PathVariable int id){
        petClient.update(pet, id);
    }

    @DeleteMapping("/pets/{id}")
    public void delete(@PathVariable int id){
        petClient.delete(id);
    }
}
