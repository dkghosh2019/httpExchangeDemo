package com.gajanan.pet_factory.controller;

import com.gajanan.pet_factory.model.Pet;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/pets")
public class PetFactoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger("PetFactoryController.class");

    private static final List<Pet> pets = new ArrayList<>();

    @PostConstruct
    private void init(){
        pets.add(new Pet(1, "Browny", "cat", "chicken"));
        pets.add(new Pet(2, "Sweetie", "cat", "chicken-tuna"));
    }

    @GetMapping
    public List<Pet> findAll(){
        LOGGER.info("PetFactoryController findAll");
        return pets;
    }

    @GetMapping("/{petId}")
    public Optional<Pet> findById(@PathVariable int petId){
        LOGGER.info("PetFactoryController findById(): {}", petId);
        return Optional.of(pets.stream()
                .filter(pet -> pet.id() == petId)
                .findFirst()
                .orElseThrow());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Pet pet){
        LOGGER.info("PetFactoryController save() : {}", pet);
        pets.add(pet);
    }

    @PutMapping("/{petId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Pet pet, @PathVariable int petId){
        LOGGER.info("PetFactoryController update() : {}", pet);
        Optional<Pet> petToUpdate = pets.stream()
                .filter(p -> p.id() == petId)
                .findFirst();

        petToUpdate.ifPresent( p -> pets.set(pets.indexOf(p), pet));

    }

    @DeleteMapping("/{petId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int petId){
        LOGGER.info("PetFactoryController delete() : {}", petId);
        pets.removeIf( pet -> pet.id()==petId);
    }

}
