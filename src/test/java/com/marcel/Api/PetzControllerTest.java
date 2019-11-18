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
        final List<String> ids = controller.createPets(ImmutableList.of(pet, pet, pet, pet, pet)).subList(0, 2);
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

    @Test
    void findOne() {
        //given
        final Pet birdPet = Pet.builder()
                .id(UUID.randomUUID())
                .name("leatherface")
                .type("bird")
                .sex("f")
                .description("it's a bird, what'd you expect")
                .ownerEmail("birdlover@gmail.com")
                .imageUrl("http://datBird.com/Mybird3")
                .build();
        final Pet dogPet = Pet.builder()
                .id(UUID.randomUUID())
                .name("rex")
                .type("dog")
                .sex("f")
                .description("it's a dog, a magnificent beast")
                .ownerEmail("birdlover@gmail.com")
                .imageUrl("http://datBird.com/Mydog1")
                .build();
        final Pet searchCriteria = Pet.builder()
                .name("leatherface")
                .type("bird")
                .build();
        controller.pets.addAll(ImmutableList.of(birdPet, dogPet));
        //when
        final List<Pet> foundPets = controller.searchPetsEndpoint(searchCriteria);
        //then
        assertThat(foundPets).hasSize(1);
        assertThat(foundPets.get(0).getName()).isEqualTo(birdPet.getName());
    }

    @Test
    void findMultiple() {
        //given
        final Pet birdPet = Pet.builder()
                .id(UUID.randomUUID())
                .name("leatherface")
                .type("bird")
                .sex("f")
                .description("it's a bird, what'd you expect")
                .ownerEmail("birdlover@gmail.com")
                .imageUrl("http://datBird.com/Mybird3")
                .build();
        final Pet dogPet = Pet.builder()
                .id(UUID.randomUUID())
                .name("rex")
                .type("dog")
                .sex("f")
                .description("it's a dog, a magnificent beast")
                .ownerEmail("birdlover@gmail.com")
                .imageUrl("http://datBird.com/Mydog1")
                .build();
        final Pet searchCriteria = Pet.builder()
                .sex("f")
                .build();
        controller.pets.addAll(ImmutableList.of(birdPet, dogPet));
        //when
        final List<Pet> foundPets = controller.searchPetsEndpoint(searchCriteria);
        //then
        assertThat(foundPets).hasSize(2);
        assertThat(foundPets.get(0).getName()).isEqualTo(birdPet.getName());
        assertThat(foundPets.get(1).getName()).isEqualTo(dogPet.getName());
    }

    @Test
    void findNone() {
        //given
        final Pet birdPet = Pet.builder()
                .id(UUID.randomUUID())
                .name("leatherface")
                .type("bird")
                .sex("f")
                .description("it's a bird, what'd you expect")
                .ownerEmail("birdlover@gmail.com")
                .imageUrl("http://datBird.com/Mybird3")
                .build();
        final Pet searchCriteria = Pet.builder()
                .name("dog")
                .build();
        controller.pets.add(birdPet);
        //when
        final List<Pet> foundPets = controller.searchPetsEndpoint(searchCriteria);
        //then
        assertThat(foundPets).hasSize(0);
    }
}