package com.rapid7.example.pact.server.demo;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactUrl;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.spring.SpringRestPactRunner;
import com.google.common.collect.Maps;
import com.rapid7.example.pact.server.demo.controllers.AnimalController;
import com.rapid7.example.pact.server.demo.model.Animal;
import com.rapid7.example.pact.server.demo.service.AnimalService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collection;
import java.util.Map;

import static org.mockito.Mockito.when;

@RunWith(SpringRestPactRunner.class)
@Provider("AnimalApi")
@PactUrl(urls = {
    "../Client/target/site/pacts/AnimalClient-AnimalApi.json"
})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AnimalServicePactTest {


    @MockBean
    private AnimalService animalService;

    private AnimalController animalController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        animalController = new AnimalController(animalService);
    }

    @TestTarget
    @SuppressWarnings(value="VisibilityModifier")
    public final Target target = new HttpTarget(7001);

    @State("Some Animals")
    public void getAllAnimals() {
        when(animalService.getAllAnimals())
            .thenReturn(mockAnimals());
    }

    @State("Some Animals")
    public void getOneAnimal() {
        when(animalService.getAnimal("Tony"))
            .thenReturn(mockAnimal());
    }

    private Animal mockAnimal() {
        return new Animal("Tony","Tiger","Orange");
    }

    private Collection<Animal> mockAnimals() {

        Map<String, Animal> animalDB = Maps.newHashMap();

        animalDB.put("Tony", new Animal("Tony","Tiger","Orange"));
        animalDB.put("Orville", new Animal("Orville","Duck","Green"));
        animalDB.put("Elmo", new Animal("Elmo","Muppet","Red"));
        animalDB.put("Dory", new Animal("Dory","Fish","Blue"));
        animalDB.put("Ben", new Animal("Ben","Bear","Brown"));
        animalDB.put("Ed", new Animal("Ed","Horse","Black"));
        animalDB.put("Flipper", new Animal("Flipper","Dolphin","Grey"));
        animalDB.put("Ryan", new Animal("Ryan","Velociraptor","Ginger"));

        return animalDB.values();
    }
}
