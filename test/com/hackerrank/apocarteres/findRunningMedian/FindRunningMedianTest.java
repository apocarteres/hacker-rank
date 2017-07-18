package com.hackerrank.apocarteres.findRunningMedian;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static java.lang.String.join;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

public final class FindRunningMedianTest {

    private Solution instance;

    @Before
    public void setUp() throws Exception {
        instance = new Solution();
    }

    @Test
    public void testCase1() throws Exception {
        List<String> input = new ArrayList<String>() {{
            add("6");
            add("12");
            add("4");
            add("5");
            add("3");
            add("8");
            add("7");
        }};

        List<String> expected = new ArrayList<String>() {{
            add("12.0");
            add("8.0");
            add("5.0");
            add("4.5");
            add("5.0");
            add("6.0");
        }};

        StringWriter out = new StringWriter();
        instance.median(new StringReader(asText(input)), out);
        assertEquals(asText(expected), out.toString());
    }

    @Test
    public void testCase2() throws Exception {
        List<String> input = new BufferedReader(
                new InputStreamReader(FindRunningMedianTest.class.getResourceAsStream(
                        "/find-running-median/test-case2-in.txt"))
        ).lines().collect(toList());
        List<String> expected = new BufferedReader(
                new InputStreamReader(FindRunningMedianTest.class.getResourceAsStream(
                        "/find-running-median/test-case2-out.txt"))
        ).lines().collect(toList());
        StringWriter out = new StringWriter();
        instance.median(new StringReader(asText(input)), out);
        assertEquals(asText(expected), out.toString());
    }

    @Test
    public void testCase3() throws Exception {
        List<String> input = new ArrayList<String>() {{
            add("6");
            add("94455");
            add("20555");
            add("20535");
            add("53125");
            add("73634");
            add("148");
        }};

        List<String> expected = new ArrayList<String>() {{
            add("94455.0");
            add("57505.0");
            add("20555.0");
            add("36840.0");
            add("53125.0");
            add("36840.0");
        }};

        StringWriter out = new StringWriter();
        instance.median(new StringReader(asText(input)), out);
        assertEquals(asText(expected), out.toString());
    }

    private static String asText(List<String> expected) {return join(format("%n"), expected);}
}