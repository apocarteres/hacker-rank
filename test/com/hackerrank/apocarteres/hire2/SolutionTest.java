package com.hackerrank.apocarteres.hire2;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;

public class SolutionTest {

    private Solution instance;

    @Before
    public void setUp() throws Exception {
        instance = new Solution();
    }

    @Test
    public void testMatchLunchesIsOk() throws Exception {
        String[][] menu = new String[][]{
                {"Pizza", "Italian"},
                {"Curry", "Indian"},
                {"Masala", "Indian"}
        };
        String[][] team = new String[][]{
                {"Jose", "Italian"},
                {"John", "Indian"},
                {"Sarah", "Thai"},
                {"Mary", "*"}
        };
        Set<String> actual = toSet(instance.matchLunches(menu, team));
        Set<String> expected = toSet(new String[][]{
                {"Jose", "Pizza"},
                {"John", "Curry"},
                {"John", "Masala"},
                {"Mary", "Masala"},
                {"Mary", "Curry"},
                {"Mary", "Pizza"},
        });
        assertEquals(expected, actual);
    }

    private static Set<String> toSet(String[][] grid) {
        Set<String> result = new HashSet<>();
        for (String[] pair : grid) {
            result.add(format("%s_%s", pair[0], pair[1]));
        }
        return result;
    }
}