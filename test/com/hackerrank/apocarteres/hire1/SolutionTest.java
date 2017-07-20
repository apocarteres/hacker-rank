package com.hackerrank.apocarteres.hire1;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;

public class SolutionTest {

    @Test
    public void testGetAnagramIndicesIsOk() throws Exception {
        Solution solution = new Solution();
        List<Integer> anagramIndices = solution.getAnagramIndices("abcacbbacbcacabcba", "abc");
        assertEquals(asList(0, 1, 3, 6, 7, 9, 12, 13, 15), anagramIndices);
    }

    @Test
    public void testGetAnagramIndicesReturnsEmpty() throws Exception {
        Solution solution = new Solution();
        List<Integer> anagramIndices = solution.getAnagramIndices("abcacbbacbcacabcba", "x");
        assertEquals(emptyList(), anagramIndices);
    }
}