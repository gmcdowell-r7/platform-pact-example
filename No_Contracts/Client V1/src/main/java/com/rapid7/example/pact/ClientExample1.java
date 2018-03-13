package com.rapid7.example.pact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@PropertySource({ "classpath:application.properties"})
public class ClientExample1 {

    protected ClientExample1(){
        new RestClient().crudAnimals();
    }

    public static void main(String[] args) {
        new ClientExample1();
    }

}
