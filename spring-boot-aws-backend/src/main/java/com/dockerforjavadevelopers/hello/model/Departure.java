package com.dockerforjavadevelopers.hello.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Departure {

    private List<StopEvent> stopEvents;

    public List<StopEvent> getStopEvents() {
        return stopEvents;
    }

}