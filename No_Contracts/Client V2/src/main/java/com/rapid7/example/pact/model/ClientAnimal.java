package com.rapid7.example.pact.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@JsonIgnoreProperties(ignoreUnknown = false)
@Getter
@Setter
@Builder
public class ClientAnimal {
    private String id;
    private String species;
    private String color;

    @JsonCreator
    public ClientAnimal (
        @JsonProperty("id") String id,
        @JsonProperty("species") String species,
        @JsonProperty("color") String color) {
        this.id = id;
        this.species = species;
        this.color = color;
    }

}
