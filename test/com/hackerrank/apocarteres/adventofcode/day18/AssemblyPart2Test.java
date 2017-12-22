package com.hackerrank.apocarteres.adventofcode.day18;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

public class AssemblyPart2Test {

    private AssemblyPart2 one;
    private AssemblyPart2 two;

    private int runParallel(String[] input1, String[] input2) throws ExecutionException, InterruptedException {
        return AssemblyPart2.runParallel(one, two, input1, input2);
    }

    @Before
    public void setUp() throws Exception {
        one = new AssemblyPart2(0);
        two = new AssemblyPart2(1);
    }

    @Test
    public void thatTransmitsSingleValue() throws Exception {
        int actual = runParallel(
                new String[]{
                        "snd x"
                },
                new String[]{
                        "rcv x",
                }
        );
        assertEquals(0, actual);
    }

    @Test
    public void thatDeadLockTerminatesProgram() throws Exception {
        int actual = runParallel(
                new String[]{
                        "rcv x"
                },
                new String[]{
                        "rcv x",
                }
        );
        assertEquals(0, actual);
    }

    @Test
    public void thatCountsSentOnSecondProgram() throws Exception {
        int actual = runParallel(
                new String[]{
                        "rcv x"
                },
                new String[]{
                        "snd x",
                        "snd 1",
                        "snd -100",
                }
        );
        assertEquals(3, actual);
    }

    @Test
    public void thatSetsValueValue() throws Exception {
        int actual = runParallel(
                new String[]{
                        "set x 15",
                        "jgz x 1",
                        "snd x",
                },
                new String[]{
                        "rcv x",
                        "snd 0",
                }
        );
        assertEquals(1, actual);
    }

    @Test
    public void thatAddsValueValue() throws Exception {
        int actual = runParallel(
                new String[]{
                        "add x 15",
                        "jgz x 1",
                        "snd x",
                },
                new String[]{
                        "rcv x",
                        "snd 0",
                }
        );
        assertEquals(1, actual);
    }

    @Test
    public void thatMultipliesValue() throws Exception {
        int actual = runParallel(
                new String[]{
                        "add x 15",
                        "mul x 0",
                        "jgz x -1",
                        "snd x",
                },
                new String[]{
                        "rcv x",
                        "snd 0",
                }
        );
        assertEquals(1, actual);
    }

    @Test
    public void thatModsValue() throws Exception {
        int actual = runParallel(
                new String[]{
                        "add x 4",
                        "mod x 4",
                        "jgz x 2",
                        "snd x",
                },
                new String[]{
                        "rcv x",
                        "snd 0",
                }
        );
        assertEquals(1, actual);
    }

    @Test
    public void thatProgramIdUpdatesRegisterPOnFirst() throws Exception {
        int actual = runParallel(
                new String[]{
                        "mul p 4",
                        "jgz p 2",
                        "rcv 0",
                        "snd x",
                },
                new String[]{
                        "rcv x",
                        "snd 0",
                }
        );
        assertEquals(0, actual);
    }

    @Test
    public void thatProgramIdUpdatesRegisterPOnSecond() throws Exception {
        int actual = runParallel(
                new String[]{
                        "rcv x",
                        "snd 0",
                },
                new String[]{
                        "mul p 4",
                        "jgz p 2",
                        "rcv 0",
                        "snd x",
                }
        );
        assertEquals(1, actual);
    }
}