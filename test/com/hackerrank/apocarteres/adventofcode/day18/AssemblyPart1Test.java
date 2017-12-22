package com.hackerrank.apocarteres.adventofcode.day18;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AssemblyPart1Test {
    @Test
    public void thatAddsValue() throws Exception {
        AssemblyPart1 assembly = new AssemblyPart1();
        int actual = assembly.exec(new String[]{
                "add x 1",
                "snd x",
                "rcv x",
        });
        assertEquals(1, actual);
    }

    @Test
    public void thatSetsValue() throws Exception {
        AssemblyPart1 assembly = new AssemblyPart1();
        int actual = assembly.exec(new String[]{
                "set x 5",
                "snd x",
                "rcv x",
        });
        assertEquals(5, actual);
    }

    @Test
    public void thatMulsValue() throws Exception {
        AssemblyPart1 assembly = new AssemblyPart1();
        int actual = assembly.exec(new String[]{
                "set x 5",
                "mul x 4",
                "snd x",
                "rcv x",
        });
        assertEquals(20, actual);
    }

    @Test
    public void thatModsValue() throws Exception {
        AssemblyPart1 assembly = new AssemblyPart1();
        int actual = assembly.exec(new String[]{
                "set x 9",
                "mod x 5",
                "snd x",
                "rcv x",
        });
        assertEquals(4, actual);
    }

    @Test
    public void thatReadsValueFromRegister() throws Exception {
        AssemblyPart1 assembly = new AssemblyPart1();
        int actual = assembly.exec(new String[]{
                "set x 9",
                "set y -2",
                "mul x y",
                "snd x",
                "rcv x",
        });
        assertEquals(-18, actual);
    }

    @Test
    public void thatJgzSkips() throws Exception {
        AssemblyPart1 assembly = new AssemblyPart1();
        int actual = assembly.exec(new String[]{
                "jgz x 10",
                "set x 1",
                "snd x",
                "rcv x",
        });
        assertEquals(1, actual);
    }

    @Test
    public void thatJgzStepsAhead() throws Exception {
        AssemblyPart1 assembly = new AssemblyPart1();
        int actual = assembly.exec(new String[]{
                "set x 1",
                "jgz x 2",
                "set x 100",
                "snd x",
                "rcv x",
        });
        assertEquals(1, actual);
    }

    @Test
    public void thatJgzStepsBack() throws Exception {
        AssemblyPart1 assembly = new AssemblyPart1();
        int actual = assembly.exec(new String[]{
                "jgz x 4",
                "set x 25",
                "jgz x -2",
                "set x 100",
                "snd x",
                "rcv x",
        });
        assertEquals(25, actual);
    }

    @Test
    public void thatRcvPlaysLatestSnd() throws Exception {
        AssemblyPart1 assembly = new AssemblyPart1();
        int actual = assembly.exec(new String[]{
                "set x 5",
                "snd x",
                "set x 64",
                "rcv x",
        });
        assertEquals(5, actual);
    }

    @Test
    public void thatInput1IdCorrect() throws Exception {
        AssemblyPart1 assembly = new AssemblyPart1();
        int actual = assembly.exec(new String[]{
                "set a 1",
                "add a 2",
                "mul a a",
                "mod a 5",
                "snd a",
                "set a 0",
                "rcv a",
                "jgz a -1",
                "set a 1",
                "jgz a -2"});
        assertEquals(4, actual);
    }
}