package com.github.bohnman.squiggly.examples.springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.text.SimpleDateFormat;

@JsonIgnoreProperties(ignoreUnknown = true)

public class StopEvent {

    private String departureTimePlanned;

    private String name;

    private Transportation transportation;

    public Transportation getTransportation() {
        return transportation;
    }

    public String getDepartureTimePlanned() {

        try {
            // 2020-06-29T19:15:00Z

            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");

            Date date = inputFormat.parse(departureTimePlanned);

            return date.getTime() + "";
        } catch (Exception e) {
            e.printStackTrace();

            return "Pending....";

        }

    }

    public String getName() {
        return name;
    }

    private Location location;

    public Location getLocation() {
        return location;
    }

}