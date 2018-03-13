package com.rapid7.example.pact.server.demo.controllers;

import com.google.common.collect.Maps;
import com.rapid7.example.pact.server.demo.model.Animal;
import com.rapid7.example.pact.server.demo.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping(value = "/v1")
public class AnimalController {

    private AnimalService animalService;

    @Autowired
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping("/animal")
    public ResponseEntity<Animal> post(
        @RequestBody Animal newAnimal) {

            return ResponseEntity.status(HttpStatus.CREATED).body(animalService.addAnimal(newAnimal));
    }

    @GetMapping("/animal/{name}")
    public ResponseEntity<Animal> get(
        @PathVariable String name) {

        Animal animal = animalService.getAnimal(name);
        if (null == animal) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(animal);
        }
    }

    @GetMapping("/animals")
    public ResponseEntity<Collection<Animal>> getAll() {
        return ResponseEntity.ok().body(animalService.getAllAnimals());
    }

    @DeleteMapping("/animal/{name}")
    public ResponseEntity delete(
        @PathVariable String name) {

        animalService.deleteAnimal(name);

        return ResponseEntity.noContent().build();
    }
}
