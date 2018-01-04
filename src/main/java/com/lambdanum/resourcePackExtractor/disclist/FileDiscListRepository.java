package com.lambdanum.resourcePackExtractor.disclist;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdanum.resourcePackExtractor.Disc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class FileDiscListRepository implements DiscListRepository {

    private String filename;
    private ObjectMapper mapper = new ObjectMapper();

    public FileDiscListRepository(String filename) {
        this.filename = filename;
    }

    @Override
    public List<Disc> getDiscs() {
        try {
            String json = StringUtils.join(Files.readAllLines(Paths.get(filename)), "\n");
            return Arrays.asList(mapper.readValue(json, Disc[].class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
