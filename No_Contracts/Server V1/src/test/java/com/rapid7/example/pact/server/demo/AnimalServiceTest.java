package com.rapid7.example.pact.server.demo;

import com.google.common.collect.Maps;
import com.rapid7.example.pact.server.demo.controllers.AnimalController;
import com.rapid7.example.pact.server.demo.model.Animal;
import com.rapid7.example.pact.server.demo.service.AnimalService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AnimalServiceTest {

	private Map<String, Animal> animalDB = Maps.newHashMap();

	@Mock
	AnimalService animalService;

	AnimalController animalController;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		animalDB.put("Tony", new Animal("Tony","Tiger","Orange"));
		animalDB.put("Orville", new Animal("Orville","Duck","Green"));
		animalDB.put("Elmo", new Animal("Elmo","Muppet","Red"));
		animalDB.put("Dory", new Animal("Dory","Fish","Blue"));
		animalDB.put("Ben", new Animal("Ben","Bear","Brown"));
		animalDB.put("Ed", new Animal("Ed","Horse","Black"));
		animalDB.put("Flipper", new Animal("Flipper","Dolphin","Grey"));

		animalController = new AnimalController(animalService);
	}

	@Test
	public void testGetAllAnimals() {

		doReturn(animalDB.values()).when(animalService).getAllAnimals();

		ResponseEntity<Collection<Animal>> response = animalController.getAll();

		assertTrue(response.getStatusCodeValue() == 200);

		assertTrue(!response.getBody().isEmpty());

		response.getBody().stream().forEach(
			(animal) -> {
				assertTrue(animal.getName() != null);
				assertTrue(animal.getType() != null);
				assertTrue(animal.getColour() != null);
			}
		);
	}

	@Test
	public void testGetAnimal() {
		doReturn(animalDB.get("Tony")).when(animalService).getAnimal(anyString());

		ResponseEntity<Animal> response = animalController.get("Tony");

		assertTrue(response.getStatusCodeValue() == 200);

		assertTrue(response.getBody() != null);

		assertTrue(response.getBody().getName() != null);
		assertTrue(response.getBody().getType() != null);
		assertTrue(response.getBody().getColour() != null);
	}

	@Test
	public void testCreateAnimal() {

		doReturn(new Animal("Buzz","Buzzard","Brown")).when(animalService).addAnimal(any());

		ResponseEntity<Animal> response = animalController.post(
			new Animal("Buzz","Buzzard","Brown"));

		assertTrue(response.getStatusCodeValue() == 201);

		assertTrue(response.getBody() != null);

		assertTrue(response.getBody().getName() != null);
		assertTrue(response.getBody().getType() != null);
		assertTrue(response.getBody().getColour() != null);
	}

	@Test
	public void testDeleteAnimal() {

		doNothing().when(animalService).deleteAnimal(anyString());

		ResponseEntity<Animal> response = animalController.delete("Tony");

		assertTrue(response.getStatusCodeValue() == 204);

	}
}
