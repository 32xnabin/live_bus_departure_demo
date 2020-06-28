package com.github.bohnman.squiggly.examples.springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Destination {

    private String name;

    public String getName() {
        return name;
    }

}