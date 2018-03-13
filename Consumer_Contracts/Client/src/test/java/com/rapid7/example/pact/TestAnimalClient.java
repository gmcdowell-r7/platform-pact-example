package com.rapid7.example.pact;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
public class TestAnimalClient {

    private final static String ANIMALS_BODY = "[\n" +
        "    {\n" +
        "        \"id\": \"Tony\",\n" +
        "        \"species\": \"Tiger\",\n" +
        "        \"color\": \"Orange\"\n" +
        "    },\n" +
        "    {\n" +
        "        \"id\": \"Orville\",\n" +
        "        \"species\": \"Duck\",\n" +
        "        \"color\": \"Green\"\n" +
        "    },\n" +
        "    {\n" +
        "        \"id\": \"Elmo\",\n" +
        "        \"species\": \"Muppet\",\n" +
        "        \"color\": \"Red\"\n" +
        "    },\n" +
        "    {\n" +
        "        \"id\": \"Dory\",\n" +
        "        \"species\": \"Fish\",\n" +
        "        \"color\": \"Blue\"\n" +
        "    },\n" +
        "    {\n" +
        "        \"id\": \"Ben\",\n" +
        "        \"species\": \"Bear\",\n" +
        "        \"color\": \"Brown\"\n" +
        "    },\n" +
        "    {\n" +
        "        \"id\": \"Ed\",\n" +
        "        \"species\": \"Horse\",\n" +
        "        \"color\": \"Black\"\n" +
        "    },\n" +
        "    {\n" +
        "        \"id\": \"Flipper\",\n" +
        "        \"species\": \"Dolphin\",\n" +
        "        \"color\": \"Grey\"\n" +
        "    },\n" +
        "    {\n" +
        "        \"id\": \"Ryan\",\n" +
        "        \"species\": \"Velociraptor\",\n" +
        "        \"color\": \"Ginger\"\n" +
        "    }\n" +
        "]";

    private final static String ANIMAL_BODY = "{\n" +
        "    \"id\": \"Tony\",\n" +
        "    \"species\": \"Tiger\",\n" +
        "    \"color\": \"Orange\"\n" +
        "}";

    private final static String ANIMAL_POST_BODY = "{\n" +
        "    \"id\": \"Buzz\",\n" +
        "    \"species\": \"Buzzard\",\n" +
        "    \"color\": \"Brown\"\n" +
        "}";

    @Rule
    public PactProviderRuleMk2 mockProvider =
        new PactProviderRuleMk2("AnimalApi", "localhost", 7001, this);

    @Pact(consumer = "AnimalClient")
    public RequestResponsePact createPact(PactDslWithProvider builder) {

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        return builder
            .given("Some Animals")

            .uponReceiving("A Get Request for all animal")
            .path("/v1/animals")
            .method("GET")
            .willRespondWith()
            .status(200)
            .headers(headers)
            .body(ANIMALS_BODY)

            .uponReceiving("A Post for a new Animal")
            .path("/v1/animal")
            .method("POST")
            .body(ANIMAL_POST_BODY)
            .willRespondWith()
            .status(201)
            .headers(headers)
            .body(ANIMAL_POST_BODY)

            .uponReceiving("A Get Request for an animal")
            .path("/v1/animal/Tony")
            .method("GET")
            .willRespondWith()
            .status(200)
            .headers(headers)
            .body(ANIMAL_BODY)

            .uponReceiving("A Delete Request for an animal")
            .path("/v1/animal/Dory")
            .method("DELETE")
            .willRespondWith()
            .headers(headers)
            .status(204)

            .given("No Animals")

            .uponReceiving("A Get Request for a missing animal")
            .path("/v1/animal/Gary")
            .method("GET")
            .willRespondWith()
            .status(404)
            .headers(headers)

            .toPact();



    }

    @Test
    @PactVerification()
    public void testRestClient() {
        new RestClient().crudAnimals();
    }
}

