package com.rapid7.example.pact;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.matching.ContainsPattern;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

@RunWith(SpringRunner.class)
public class TestAnimalClient {
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(7001);

    @Test
    public void testRestClient() {
        stubFor(
            get(urlEqualTo(String.format("/v1/animals")))
                .willReturn(aResponse()
                    .withStatus(200)
                    .withHeader("Content-type", "application/json")
                    .withBody("[\n" +
                        "    {\n" +
                        "        \"name\": \"Tony\",\n" +
                        "        \"type\": \"Tiger\",\n" +
                        "        \"colour\": \"Orange\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"name\": \"Dory\",\n" +
                        "        \"type\": \"Fish\",\n" +
                        "        \"colour\": \"Blue\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"name\": \"Mike\",\n" +
                        "        \"type\": \"Seagull\",\n" +
                        "        \"colour\": \"White\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"name\": \"Elmo\",\n" +
                        "        \"type\": \"Muppet\",\n" +
                        "        \"colour\": \"Red\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"name\": \"Ben\",\n" +
                        "        \"type\": \"Bear\",\n" +
                        "        \"colour\": \"Brown\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"name\": \"Orville\",\n" +
                        "        \"type\": \"Duck\",\n" +
                        "        \"colour\": \"Green\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"name\": \"Flipper\",\n" +
                        "        \"type\": \"Dolphin\",\n" +
                        "        \"colour\": \"Grey\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"name\": \"Ed\",\n" +
                        "        \"type\": \"Horse\",\n" +
                        "        \"colour\": \"Black\"\n" +
                        "    }\n" +
                        "]")
                )
        );

        stubFor(
            post(urlEqualTo(String.format("/v1/animal")))
                .withRequestBody(new ContainsPattern("{\"name\":\"Buzz\",\"type\":\"Buzzard\",\"colour\":\"Brown\"}"))
                .willReturn(aResponse()
                    .withStatus(201)
                    .withHeader("Content-type", "application/json")
                    .withBody("{\n" +
                        "    \"name\": \"Buzz\",\n" +
                        "    \"type\": \"Buzzard\",\n" +
                        "    \"colour\": \"Brown\"\n" +
                        "}")
                )
        );

        stubFor(
            get(urlEqualTo(String.format("/v1/animal/Tony")))
                .willReturn(aResponse()
                    .withStatus(200)
                    .withHeader("Content-type", "application/json")
                    .withBody("{\n" +
                        "    \"name\": \"Tony\",\n" +
                        "    \"type\": \"Tiger\",\n" +
                        "    \"colour\": \"Orange\"\n" +
                        "}")
                )
        );

        stubFor(
            delete(urlEqualTo(String.format("/v1/animal/Dory")))
                .willReturn(aResponse()
                    .withStatus(204)
                    .withHeader("Content-type", "application/json")
                )
        );

        new RestClient().crudAnimals();
    }
}
