package com.dockerforjavadevelopers.hello.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Destination {

    private String name;

    public String getName() {
        return name;
    }

}