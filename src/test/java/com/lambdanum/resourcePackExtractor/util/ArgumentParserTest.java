package com.lambdanum.resourcePackExtractor.util;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

public class ArgumentParserTest {

    private ArgumentParser argumentParser = new ArgumentParser();

    @Test
    public void givenKeyArgument_whenParsing_thenArgumentsContainsKey() {
        String programExecutionCall = "--key value";

        Map<String, String> arguments = argumentParser.parse(programExecutionCall.split(" "));

        assertEquals("value", arguments.get("key"));
    }

    @Test
    public void givenKeyFollowedByTwoValues_whenParsing_thenSecondValueIsIgnored() {
        String programExecutionCall = "--key value ignored";

        Map<String, String> arguments = argumentParser.parse(programExecutionCall.split(" "));

        assertEquals("value", arguments.get("key"));
        assertFalse(arguments.values().contains("ignored"));
    }

}
