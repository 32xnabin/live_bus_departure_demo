package com.github.bohnman.squiggly.examples.springboot.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Stops {

    private List<Location> locations;

    public List<Location> getLocations() {
        return locations;
    }

}