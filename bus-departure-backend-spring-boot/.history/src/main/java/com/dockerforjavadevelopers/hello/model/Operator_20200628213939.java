package com.github.bohnman.squiggly.examples.springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Operator {

    private String id;
    private String name;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

}