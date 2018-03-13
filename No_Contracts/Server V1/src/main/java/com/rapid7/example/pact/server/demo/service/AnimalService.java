package com.rapid7.example.pact.server.demo.service;

import com.google.common.collect.Maps;
import com.rapid7.example.pact.server.demo.model.Animal;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

@Service
public class AnimalService {
    private Map<String, Animal> animalDB = Maps.newHashMap();

    public AnimalService () {
        animalDB.put("Tony", new Animal("Tony","Tiger","Orange"));
        animalDB.put("Orville", new Animal("Orville","Duck","Green"));
        animalDB.put("Elmo", new Animal("Elmo","Muppet","Red"));
        animalDB.put("Dory", new Animal("Dory","Fish","Blue"));
        animalDB.put("Ben", new Animal("Ben","Bear","Brown"));
        animalDB.put("Ed", new Animal("Ed","Horse","Black"));
        animalDB.put("Flipper", new Animal("Flipper","Dolphin","Grey"));
        animalDB.put("Ryan", new Animal("Ryan","Velociraptor","Ginger"));
    }

    public Collection<Animal> getAllAnimals() {
        return animalDB.values();
    }

    public Animal getAnimal(String name) {
        return animalDB.get(name);
    }

    public Animal addAnimal(Animal animal) {
        animalDB.put(animal.getName(), animal);
        return animal;
    }

    public void deleteAnimal(String name) {
        animalDB.remove(name);
    }
}
