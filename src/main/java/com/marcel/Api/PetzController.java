package com.marcel.Api;

import com.google.common.collect.ImmutableList;
import com.marcel.Api.Model.Pet;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PetzController {

    @PostMapping("/createPets")
    List<String> createPets(@RequestBody List<Pet> pets) {
        return pets.stream().map(Pet::getName).collect(Collectors.toList());
    }

    @PutMapping("/updatePets")
    String updatePets(String hello) {
        return "Update made " + hello;
    }

    @DeleteMapping("/deletePets")
    String deletePets(String hello) {
        return "deleted";
    }

    @DeleteMapping("/deletePets/{id}")
    String deletePetsById(@PathVariable String id) {
        return "deleted";
    }

    @GetMapping("/searchPets")
    List<String> searchPets(String hello) {
        return ImmutableList.of(hello, hello, hello);
    }


}
