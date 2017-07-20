package com.hackerrank.apocarteres.swapNodes;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static java.lang.String.join;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

public class SwapNodesTest {

    private static final Node NULL_NODE = new Node(-1);

    private Solution instance;

    @Before
    public void setUp() throws Exception {
        instance = new Solution();
    }

    @Test
    public void integrationCase1() throws Exception {
        String input = asText("/swap-nodes/test-case1-in.txt");
        String expected = asText("/swap-nodes/test-case1-out.txt");
        StringWriter out = new StringWriter();
        BufferedWriter writer = new BufferedWriter(out);
        instance.solve(new BufferedReader(new StringReader(input)), writer);
        writer.close();
        String actual = out.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void integrationCase2() throws Exception {
        String input = asText("/swap-nodes/test-case2-in.txt");
        String expected = asText("/swap-nodes/test-case2-out.txt");
        StringWriter out = new StringWriter();
        BufferedWriter writer = new BufferedWriter(out);
        instance.solve(new BufferedReader(new StringReader(input)), writer);
        writer.close();
        String actual = out.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void integrationCase3() throws Exception {
        String input = asText("/swap-nodes/test-case3-in.txt");
        String expected = asText("/swap-nodes/test-case3-out.txt");
        StringWriter out = new StringWriter();
        BufferedWriter writer = new BufferedWriter(out);
        instance.solve(new BufferedReader(new StringReader(input)), writer);
        writer.close();
        String actual = out.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void thatTreeBuiltWell() throws Exception {
        List<String> input = new ArrayList<String>() {{
            add("6");
            add("2 3");
            add("4 -1");
            add("-1 5");
            add("8 -1");
            add("-1 -1");
            add("-1 -1");
        }};
        Node expected =
                new Node(1).with(
                        new Node(2).with(
                                new Node(4).with(
                                        new Node(8), NULL_NODE
                                ),
                                NULL_NODE
                        ),
                        new Node(3).with(
                                NULL_NODE,
                                new Node(5)
                        )
                );
        Node actual = instance.build(new BufferedReader(new StringReader(asText(input))));
        assertEquals(expected, actual);
    }

    @Test
    public void thatSwapOnLevelWell() throws Exception {
        Node tree =
                new Node(1).with(
                        new Node(2),
                        new Node(3).with(
                                NULL_NODE,
                                new Node(5)
                        )
                );
        Node expected =
                new Node(1).with(
                        new Node(3).with(
                                NULL_NODE,
                                new Node(5)
                        ),
                        new Node(2)
                );
        instance.swap(tree, 0, 1);
        assertEquals(expected, tree);
    }

    @Test
    public void thatLeafsSwapOnLevelWell() throws Exception {
        Node tree =
                new Node(1).with(
                        new Node(2).with(new Node(8), NULL_NODE),
                        new Node(3).with(NULL_NODE, new Node(5))
                );
        Node expected =
                new Node(1).with(
                        new Node(2).with(NULL_NODE, new Node(8)),
                        new Node(3).with(new Node(5), NULL_NODE)
                );
        instance.swap(tree, 0, 1);
        assertEquals(expected, tree);
    }

    @Test
    public void thatSinkFromLevelWell() throws Exception {
        Node tree =
                new Node(1).with(
                        new Node(2).with(NULL_NODE, new Node(8)),
                        new Node(3).with(NULL_NODE, new Node(5))
                );
        List<Integer> expected = new ArrayList<Integer>() {{
            add(2);
            add(8);
            add(1);
            add(3);
            add(5);
        }};
        List<Integer> actual = instance.sink(tree);
        assertEquals(expected, actual);
    }

    @Test
    public void thatPrintsFromLevelWell() throws Exception {
        String expected = "5 7 13 4";
        StringWriter writer = new StringWriter();
        instance.print(asList(5, 7, 13, 4), writer);
        assertEquals(expected, writer.toString());
    }

    private static String asText(List<String> expected) {return join(format("%n"), expected);}

    private static String asText(String uri) {
        return asText(
                new BufferedReader(
                        new InputStreamReader(
                                SwapNodesTest.class.getResourceAsStream(uri))
                ).lines().collect(toList())
        );
    }
}