package com.rapid7.example.pact;

    import com.github.tomakehurst.wiremock.junit.WireMockRule;
    import com.github.tomakehurst.wiremock.matching.ContainsPattern;
    import com.rapid7.example.pact.swagger.invoker.ApiException;
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
                        "        \"id\": \"Tony\",\n" +
                        "        \"species\": \"Tiger\",\n" +
                        "        \"color\": \"Orange\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"id\": \"Dory\",\n" +
                        "        \"species\": \"Fish\",\n" +
                        "        \"color\": \"Blue\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"id\": \"Mike\",\n" +
                        "        \"species\": \"Seagull\",\n" +
                        "        \"color\": \"White\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"id\": \"Elmo\",\n" +
                        "        \"species\": \"Muppet\",\n" +
                        "        \"color\": \"Red\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"id\": \"Ben\",\n" +
                        "        \"species\": \"Bear\",\n" +
                        "        \"color\": \"Brown\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"id\": \"Orville\",\n" +
                        "        \"species\": \"Duck\",\n" +
                        "        \"color\": \"Green\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"id\": \"Flipper\",\n" +
                        "        \"species\": \"Dolphin\",\n" +
                        "        \"color\": \"Grey\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"id\": \"Ed\",\n" +
                        "        \"species\": \"Horse\",\n" +
                        "        \"color\": \"Black\"\n" +
                        "    }\n" +
                        "]")
                )
        );

        stubFor(
            post(urlEqualTo(String.format("/v1/animal")))
                .withRequestBody(new ContainsPattern("{\"id\":\"Buzz\",\"species\":\"Buzzard\",\"color\":\"Brown\"}"))
                .willReturn(aResponse()
                    .withStatus(201)
                    .withHeader("Content-type", "application/json")
                    .withBody("{\n" +
                        "    \"id\": \"Buzz\",\n" +
                        "    \"species\": \"Buzzard\",\n" +
                        "    \"color\": \"Brown\"\n" +
                        "}")
                )
        );

        stubFor(
            get(urlEqualTo(String.format("/v1/animal/Tony")))
                .willReturn(aResponse()
                    .withStatus(200)
                    .withHeader("Content-type", "application/json")
                    .withBody("{\n" +
                        "    \"id\": \"Tony\",\n" +
                        "    \"species\": \"Tiger\",\n" +
                        "    \"color\": \"Orange\"\n" +
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

        try {
            new RestClient().crudAnimals();
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }
}

