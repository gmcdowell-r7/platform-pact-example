package com.rapid7.example.pact.server.demo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@JsonIgnoreProperties(ignoreUnknown = false)
@Getter
@Setter
@Builder
public class Animal {
    private String name;
    private String type;
    private String colour;

    @JsonCreator
    public Animal (
        @JsonProperty("name") String name,
        @JsonProperty("type") String type,
        @JsonProperty("colour") String colour) {
            this.name = name;
            this.type = type;
            this.colour = colour;
    }
}
