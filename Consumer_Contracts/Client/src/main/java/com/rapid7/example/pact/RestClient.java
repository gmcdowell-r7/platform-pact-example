package com.rapid7.example.pact;

import com.rapid7.example.pact.model.ClientAnimal;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;

public class RestClient {

    private static final String host = "http://localhost:7001/v1/";

    public void crudAnimals() {
        ResponseEntity<ArrayList> getAllResponse = new RestTemplate()
            .getForEntity(host + "animals", ArrayList.class);

        System.out.println("==========================================================");
        System.out.println("===================  Get All =============================");
        System.out.println("==========================================================");
        System.out.println();

        getAllResponse.getBody().stream().forEach(
            (animal) -> {
                System.out.println(animal);
            }
        );

        System.out.println();
        System.out.println("==========================================================");
        System.out.println("======================  Post =============================");
        System.out.println("==========================================================");
        System.out.println();

        ClientAnimal animal = new ClientAnimal("Buzz", "Buzzard", "Brown");


        ResponseEntity<ClientAnimal> postResponse = new RestTemplate().exchange(
            host + "animal",
            HttpMethod.POST,
            new HttpEntity<>(animal),
            ClientAnimal.class
        );

        System.out.println(postResponse.getStatusCode());
        System.out.println(postResponse.getBody());

        System.out.println("==========================================================");
        System.out.println("===================  Get One =============================");
        System.out.println("==========================================================");
        System.out.println();

        ResponseEntity<ClientAnimal> getResponse = new RestTemplate()
            .getForEntity(host + "animal/Tony", ClientAnimal.class);

        System.out.println(getResponse.getStatusCode());
        System.out.println(getResponse.getBody());


        System.out.println();
        System.out.println("==========================================================");
        System.out.println("======================  Delete ===========================");
        System.out.println("==========================================================");
        System.out.println();


        ResponseEntity<String> deleteResponse = new RestTemplate().exchange(
            host + "animal/Dory",
            HttpMethod.DELETE,
            null,
            String.class);

        System.out.println(deleteResponse.getStatusCode());

        System.out.println("==========================================================");
        System.out.println("===================  Get All =============================");
        System.out.println("==========================================================");
        System.out.println();
        getAllResponse = new RestTemplate()
            .getForEntity(host + "animals", ArrayList.class);

        getAllResponse.getBody().stream().forEach(
            (animal2) -> {
                System.out.println(animal2);
            }
        );

        System.out.println("==========================================================");
        System.out.println("===================  Get One =============================");
        System.out.println("==========================================================");
        System.out.println();

        try {
            getResponse = new RestTemplate()
                .getForEntity(host + "animal/Gary", ClientAnimal.class);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
