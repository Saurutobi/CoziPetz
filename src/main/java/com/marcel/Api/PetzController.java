package com.marcel.Api;

import com.google.common.collect.ImmutableList;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PetzController {

    @GetMapping("/helloWorld")
    String helloWorld() {
        return "you got me";
    }

    @PostMapping("/createPets")
    List<String> createPets(String hello) {
        return ImmutableList.of(hello, hello);
    }

    @PutMapping("/updatePets")
    String updatePets(String hello) {
        return "Update made " + hello;
    }

    @DeleteMapping("/deletePets")
    String deletePets(String hello) {
        return "deleted";
    }

    @GetMapping("/searchPets")
    List<String> searchPets(String hello) {
        return ImmutableList.of(hello, hello, hello);
    }


}
