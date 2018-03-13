package com.rapid7.example.pact;

import com.rapid7.example.pact.swagger.api.DefaultApi;
import com.rapid7.example.pact.swagger.invoker.ApiClient;
import com.rapid7.example.pact.swagger.invoker.ApiException;
import com.rapid7.example.pact.swagger.model.Animal;
import com.rapid7.example.pact.swagger.model.Animals;

import java.util.Collection;

public class RestClient {

    private static final String host = "http://localhost:7001/v1";

    public void crudAnimals() throws ApiException {
        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath(host);

        DefaultApi animalApi = new DefaultApi(apiClient);

        Animals getAllResponse = animalApi.getAll();

        System.out.println("==========================================================");
        System.out.println("===================  Get All =============================");
        System.out.println("==========================================================");
        System.out.println();

        getAllResponse.stream().forEach(
            (animal) -> {
                System.out.println(animal);
            }
        );

        System.out.println();
        System.out.println("==========================================================");
        System.out.println("======================  Post =============================");
        System.out.println("==========================================================");
        System.out.println();

        Animal animal = new Animal();

        animal.setId("Buzz");
        animal.setSpecies("Buzzard");
        animal.setColor("Brown");

        Animal postResponse = animalApi.post(animal);

        System.out.println(postResponse);

        System.out.println("==========================================================");
        System.out.println("===================  Get One =============================");
        System.out.println("==========================================================");
        System.out.println();

        Animal getResponse = animalApi.get("Tony");

        System.out.println(getResponse);


        System.out.println();
        System.out.println("==========================================================");
        System.out.println("======================  Delete ===========================");
        System.out.println("==========================================================");
        System.out.println();


        animalApi.deleteAnimal("Dory");

        System.out.println("==========================================================");
        System.out.println("===================  Get All =============================");
        System.out.println("==========================================================");
        System.out.println();
        getAllResponse = animalApi.getAll();

        getAllResponse.stream().forEach(
            (animal2) -> {
                System.out.println(animal2);
            }
        );
    }

}
