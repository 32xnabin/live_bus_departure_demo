package com.dockerforjavadevelopers.hello.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {

    private String id;
    private String name;
    private int[] coord;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int[] getCoord() {
        return coord;
    }

}