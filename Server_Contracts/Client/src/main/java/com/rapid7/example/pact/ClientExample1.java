package com.rapid7.example.pact;

import com.rapid7.example.pact.swagger.invoker.ApiException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@PropertySource({ "classpath:application.properties"})
public class ClientExample1 {

    protected ClientExample1(){
        try {
            new RestClient().crudAnimals();
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ClientExample1();
    }

}
