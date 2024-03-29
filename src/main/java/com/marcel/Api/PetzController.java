package com.marcel.Api;

import com.google.common.annotations.VisibleForTesting;
import com.marcel.Api.Model.Pet;
import io.vavr.control.Option;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("UnusedReturnValue")
@RestController
public class PetzController {
    @VisibleForTesting
    List<Pet> pets = new ArrayList<Pet>();

    @PostMapping("/createPets")
    List<String> createPetsEndpoint(@RequestBody List<Pet> createPets) {
        return createPets(createPets);
    }

    @DeleteMapping("/deletePets/all")
    String deleteAllPetsEndpoint() {
        pets.clear();
        return "Deleted All Pets";
    }

    @GetMapping("/allPets")
    List<Pet> allPetsEndpoints() {
        return pets;
    }

    @DeleteMapping("/deletePets")
    String deletePetsEndpoint(@RequestBody List<String> ids) {
        ids.forEach(this::deletePetById);
        return "Deleted pets with specified Ids";
    }

    @DeleteMapping("/deletePet/{id}")
    String deletePetByIdEndpoint(@PathVariable String id) {
        deletePetById(id);
        return "Deleted pet";
    }

    @PutMapping("/updatePets")
    void updatePetsEndpoint(@RequestBody List<Pet> updatePets) {
        updatePets.stream()
                .filter(pet -> pet.id != null)
                .forEach(this::updatePet);
    }

    @GetMapping("/searchPets")
    List<Pet> searchPetsEndpoint(Pet searchCriteria) {
        final Dictionary foundPets = new Hashtable();
        Option.of(searchCriteria.getId()).peek(id -> pets.stream().filter(pet -> pet.id.equals(id)).forEach(pet -> foundPets.put(pet.id, pet)));
        Option.of(searchCriteria.getId()).peek(id -> pets.stream().filter(pet -> pet.id.equals(id)).forEach(pet -> foundPets.put(pet.id, pet)));
        Option.of(searchCriteria.getName()).peek(name -> pets.stream().filter(pet -> pet.name.equals(name)).forEach(pet -> foundPets.put(pet.id, pet)));
        Option.of(searchCriteria.getType()).peek(type -> pets.stream().filter(pet -> pet.type.equals(type)).forEach(pet -> foundPets.put(pet.id, pet)));
        Option.of(searchCriteria.getSex()).peek(sex -> pets.stream().filter(pet -> pet.sex.equals(sex)).forEach(pet -> foundPets.put(pet.id, pet)));
        Option.of(searchCriteria.getDescription()).peek(description -> pets.stream().filter(pet -> pet.description.equals(description)).forEach(pet -> foundPets.put(pet.id, pet)));
        Option.of(searchCriteria.getOwnerEmail()).peek(ownerEmail -> pets.stream().filter(pet -> pet.ownerEmail.equals(ownerEmail)).forEach(pet -> foundPets.put(pet.id, pet)));
        Option.of(searchCriteria.getImageUrl()).peek(imageUrl -> pets.stream().filter(pet -> pet.imageUrl.equals(imageUrl)).forEach(pet -> foundPets.put(pet.id, pet)));
        return Collections.list(foundPets.elements());
    }

    @VisibleForTesting
    void deletePetById(String id) {
        pets.stream()
                .filter(pet -> pet.id.toString().equals(id))
                .findFirst()
                .ifPresent(pet -> pets.remove(pets.indexOf(pet)));
    }

    @VisibleForTesting
    List<String> createPets(List<Pet> createPets) {
        createPets.forEach(pet -> pet.setId(UUID.randomUUID()));
        pets.addAll(createPets);
        return createPets.stream().map(Pet::getId).map(UUID::toString).collect(Collectors.toList());
    }

    @VisibleForTesting
    void updatePet(Pet updatePet) {
        pets.stream().filter(pet -> pet.getId().equals(updatePet.getId())).findFirst().ifPresent(pet -> {
            Option.of(updatePet.getName()).peek(pet::setName);
            Option.of(updatePet.getType()).peek(pet::setType);
            Option.of(updatePet.getSex()).peek(pet::setSex);
            Option.of(updatePet.getDescription()).peek(pet::setDescription);
            Option.of(updatePet.getOwnerEmail()).peek(pet::setOwnerEmail);
            Option.of(updatePet.getImageUrl()).peek(pet::setImageUrl);
        });
    }
}
