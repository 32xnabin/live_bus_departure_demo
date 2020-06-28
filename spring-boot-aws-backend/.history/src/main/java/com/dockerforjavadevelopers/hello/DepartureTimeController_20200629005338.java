package com.dockerforjavadevelopers.hello;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;

import org.springframework.http.MediaType;
import java.util.Arrays;
import org.springframework.web.client.RestTemplate;
import com.dockerforjavadevelopers.hello.model.Stops;
import com.dockerforjavadevelopers.hello.model.Departure;
import java.util.Date;
import java.text.SimpleDateFormat;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class DepartureTimeController {

    private String url = "https://api.transport.nsw.gov.au/v1/tp/stop_finder?outputFormat=rapidJSON&type_sf=stop&name_sf=Bondi%20Beach&coordOutputFormat=EPSG%3A4326&TfNSWSF=true&version=10.2.1.42";
    private String url1 = "https://api.transport.nsw.gov.au/v1/tp/stop_finder?outputFormat=rapidJSON&type_sf=stop&coordOutputFormat=EPSG%3A4326&TfNSWSF=true&version=10.2.1.42&name_sf=";
    private String depUrl = "https://api.transport.nsw.gov.au/v1/tp/departure_mon?outputFormat=rapidJSON&coordOutputFormat=EPSG%3A4326&mode=direct&TfNSWDM=true&version=10.2.2.48&type_dm=stop&name_dm=";

    @RequestMapping("/stops")
    public ResponseEntity findStops() {
        // return ListResponse.of(Issue.findAll());
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        headers.set("Authorization", "apikey BfqiIMsflJyNZr5P2MDJqFB7juVfkqH6einf");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        return restTemplate.exchange(url, HttpMethod.GET, entity, Stops.class);

    }

    @RequestMapping("/stops/{name}")
    public ResponseEntity findStopsByName(@PathVariable String name) {
        // return ListResponse.of(Issue.findAll());
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        headers.set("Authorization", "apikey BfqiIMsflJyNZr5P2MDJqFB7juVfkqH6einf");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        return restTemplate.exchange(url1 + name, HttpMethod.GET, entity, Stops.class);

    }

    @RequestMapping("/departures/{stopId}")
    public ResponseEntity findDeparturesByStopId(@PathVariable String stopId) {

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat inputFormat1 = new SimpleDateFormat("HH:mm");

        String date = inputFormat.format(new Date());
        String time = inputFormat1.format(new Date().getTime() + (60000 * 60));

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", "apikey BfqiIMsflJyNZr5P2MDJqFB7juVfkqH6einf");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        // System.out.println("--->" + date);
        // System.out.println("--->" + time);

        return restTemplate.exchange(depUrl + stopId + "&itdDate=" + date + "&itdTime=" + time, HttpMethod.GET, entity,
                Departure.class);

    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse onNotFound(NotFoundException exception) {
        return new ErrorResponse(exception.getMessage());
    }

}
