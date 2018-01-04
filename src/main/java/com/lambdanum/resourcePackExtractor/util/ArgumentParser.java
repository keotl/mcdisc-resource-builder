package com.lambdanum.resourcePackExtractor.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class ArgumentParser {

    public Map<String, String> parse(String[] args) {
        Map<String, String> arguments = new HashMap<>();

        String previousKey = "";
        for (String word : args) {
            if (isKey(word)) {
                previousKey = word;
                continue;
            }
            if (StringUtils.isNotBlank(previousKey)) {
                arguments.put(removeLeadingDashes(previousKey), word);
                previousKey = "";
            }
        }

        return arguments;
    }

    private String removeLeadingDashes(String key) {
        return key.substring(2, key.length());
    }

    private boolean isKey(String word) {
        return word.startsWith("--");
    }
}
