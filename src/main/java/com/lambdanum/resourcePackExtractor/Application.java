package com.lambdanum.resourcePackExtractor;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdanum.resourcePackExtractor.disclist.RestDiscListRepository;
import com.lambdanum.resourcePackExtractor.util.ArgumentParser;
import com.lambdanum.soundsjson.Entry;
import com.lambdanum.soundsjson.Sound;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {

    private static ObjectMapper mapper = new ObjectMapper();

    private static String defaultDiscListLocation = "https://raw.githubusercontent.com/KEOTL/mcdisc/master/sample-disc-config.json";
    private static String targetDirectory;

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("missing arguments <path>");
            System.out.println("Usage: resourceBuilder <path> [--disc-list DISC_LIST_LOCATION] [--pool-size 10] [--max-retry 5]");
            return;
        }
        targetDirectory = args[0];

        ArgumentParser argumentParser = new ArgumentParser();
        Map<String, String> arguments = argumentParser.parse(args);

        String discListLocation = arguments.getOrDefault("disc-list", defaultDiscListLocation);
        String poolSize = arguments.getOrDefault("pool-size", "20");
        String maxRetry = arguments.getOrDefault("max-retry", "5");

        List<Disc> discs = getMcDiscList(discListLocation);
        prepareSoundsJson(discs);
        prepareLang(discs);
        DownloaderPool pool = new DownloaderPool(Integer.parseInt(poolSize), targetDirectory);
        DownloaderThread.MAX_RETRY = Integer.parseInt(maxRetry);

        for (Disc disc : discs) {
            System.out.println("Queueing " + disc.getId() + "/" + discs.size());
            pool.queueDownload(disc);
        }

        pool.join();

    }

    private static List<Disc> getMcDiscList(String discListLocation) {
        return new RestDiscListRepository(discListLocation).getDiscs();
    }

    private static void prepareSoundsJson(List<Disc> discs) {
        Map<String, Entry> soundsJson = new HashMap<>();
        for (Disc disc : discs) {
            Entry entry = new Entry();
            entry.setCategory("block");
            Sound entrySound = new Sound();
            entrySound.setName("mcdisc:" + disc.getSoundId());
            entry.setSounds(Arrays.asList(entrySound));
            soundsJson.put(disc.getSoundId(), entry);
        }

        try {
            mapper.writeValue(new File(targetDirectory + "/assets/mcdisc/sounds.json"), soundsJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void prepareLang(List<Disc> discs) {
        List<String> entries = new ArrayList<>();
        for (Disc disc : discs) {
            entries.add(formatDiscLangEntry(disc));
        }
        try {
            Files.write(Paths.get(targetDirectory + "/assets/mcdisc/lang/en_us.lang"), entries, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error writing lang");
        }
    }

    private static String formatDiscLangEntry(Disc disc) {
        return "item.record." + disc.getMinecraftId() + ".desc=" + disc.getArtist() + " - " + disc.getName();
    }
}
