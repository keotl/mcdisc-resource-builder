package com.lambdanum.resourcePackExtractor;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdanum.soundsjson.Entry;
import com.lambdanum.soundsjson.Sound;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Application {

    private static ObjectMapper mapper = new ObjectMapper();

    private static String releaseUrl = "https://boiling-forest-57763.herokuapp.com/release";
    private static String targetDirectory;

    private static final int POOL_SIZE = 10;

        public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("missing arguments <path>");
            System.out.println("Usage: resourceBuilder <path> [releaseUrl]");
            return;
        }
        targetDirectory = args[0];

        if (args.length > 1) {
            releaseUrl = args[2];
        }

        String discReleaseJson = getMcDiscRelease();
        try {
            Disc[] discs = mapper.readValue(discReleaseJson, Disc[].class);
            prepareSoundsJson(discs);
            prepareLang(discs);
            DownloaderPool pool = new DownloaderPool(POOL_SIZE, targetDirectory);

            for (Disc disc : discs) {
                System.out.println("Queueing " + disc.getId() + "/" + discs.length);
                pool.queueDownload(disc);
            }

            pool.join();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getMcDiscRelease() {
        try {
            return Unirest.get(releaseUrl).asString().getBody();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return "[]";
    }

    private static void prepareSoundsJson(Disc[] discs) {
        Map<String, Entry> soundsJson = new HashMap<>();
        for (Disc disc: discs) {
            Entry entry = new Entry();
            entry.setCategory("block");
            Sound entrySound = new Sound();
            entrySound.setName("mcdisc:" + disc.getSoundId());
            entry.setSounds(Arrays.asList(entrySound));
            soundsJson.put(disc.getSoundId(), entry);
        }

        try {
            mapper.writeValue(new File(targetDirectory + "/assets/mcdisc/sounds.json"),soundsJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void prepareLang(Disc[] discs) {
        List<String> entries = new ArrayList<>();
        for (Disc disc: discs) {
            entries.add(formatDiscLangEntry(disc));
        }
        try {
            Files.write(Paths.get(targetDirectory + "/assets/mcdisc/lang/en_us.lang"),entries, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error writing lang");
        }
    }
    private static String formatDiscLangEntry(Disc disc) {
        return "item.record." + disc.getMinecraftId() + ".desc=" + disc.getArtist() + " - " + disc.getName();
    }
}
