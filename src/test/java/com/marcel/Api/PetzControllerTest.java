package com.marcel.Api;

import com.google.common.collect.ImmutableList;
import com.marcel.Api.Model.Pet;
import org.junit.jupiter.api.Test;
import org.testng.annotations.BeforeMethod;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class PetzControllerTest {
    private PetzController controller = new PetzController();

    @BeforeMethod
    void init() {
        controller.pets.clear();
    }

    @Test
    void deleteAll() {
        //given
        final Pet pet = Pet.builder()
                .name("something")
                .build();
        controller.createPets(ImmutableList.of(pet));
        //when
        controller.deleteAllPetsEndpoint();
        //then
        assertThat(controller.pets).hasSize(0);
    }

    @Test
    void deleteById() {
        //given
        final UUID id = UUID.randomUUID();
        final Pet pet = Pet.builder()
                .id(id)
                .name("something")
                .build();
        controller.pets.add(pet);
        //when
        controller.deletePetById(id.toString());
        //then
        assertThat(controller.pets).hasSize(0);
    }

    @Test
    void deleteManyByIds() {
        //given
        final Pet pet = Pet.builder()
                .name("something")
                .build();
        final String expectedName = "something";
        final List<String> ids = controller.createPets(ImmutableList.of(pet, pet, pet, pet, pet)).subList(0,2);
        //when
        controller.deletePetsEndpoint(ids);
        //then
        assertThat(controller.pets).hasSize(3);
    }

    @Test
    void createSome() {
        //given
        final Pet pet = Pet.builder()
                .name("something")
                .build();
        final String expectedName = "something";
        //when
        final List<String> createIds = controller.createPets(ImmutableList.of(pet, pet));
        //then
        assertThat(createIds).hasSize(2);
        assertThat(controller.pets).hasSize(2);
        assertThat(controller.pets.get(0).getName()).isEqualTo(expectedName);
        assertThat(controller.pets.get(1).getName()).isEqualTo(expectedName);
    }

    @Test
    void updateOne() {
        //given
        final UUID id = UUID.randomUUID();
        final Pet pet = Pet.builder()
                .id(id)
                .name("something")
                .build();
        final Pet updatedPet = Pet.builder()
                .id(id)
                .name("newName")
                .description("blablabla")
                .build();
        controller.pets.add(pet);
        //when
        controller.updatePet(updatedPet);
        //then
        assertThat(controller.pets).hasSize(1);
        assertThat(controller.pets.get(0).getName()).isEqualTo(updatedPet.getName());
        assertThat(controller.pets.get(0).getDescription()).isEqualTo(updatedPet.getDescription());
    }
}