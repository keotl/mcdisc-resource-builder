package com.lambdanum.resourcePackExtractor.disclist;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdanum.resourcePackExtractor.Disc;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class RestDiscListRepository implements DiscListRepository {

    private String discListUrl;
    private ObjectMapper mapper = new ObjectMapper();

    public RestDiscListRepository(String discListUrl) {
        this.discListUrl = discListUrl;
    }

    @Override
    public List<Disc> getDiscs() {
        try {
            String discListJson = Unirest.get(discListUrl).asString().getBody();
            return Arrays.asList(mapper.readValue(discListJson, Disc[].class));
        } catch (UnirestException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
