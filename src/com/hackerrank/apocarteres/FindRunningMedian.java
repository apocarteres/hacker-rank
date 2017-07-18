package com.hackerrank.apocarteres;

import java.io.*;
import java.util.Comparator;
import java.util.TreeSet;

import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static java.util.Objects.isNull;

//https://www.hackerrank.com/challenges/find-the-running-median
public class FindRunningMedian {
    private static final Comparator<Integer> INTEGER_COMPARATOR = (a, b) -> a.equals(b) ? 1 : Integer.compare(a, b);

    public static void main(String[] args) throws IOException {
        new FindRunningMedian().median(new InputStreamReader(System.in), new OutputStreamWriter(System.out));
    }

    public void median(Reader in, Writer out) throws IOException {
        BufferedReader reader = new BufferedReader(in);
        int n = parseInt(reader.readLine());
        TreeSet<Integer> left = new TreeSet<>(INTEGER_COMPARATOR);
        TreeSet<Integer> right = new TreeSet<>(INTEGER_COMPARATOR);
        for (int i = 0; i < n; i++) {
            int v = parseInt(reader.readLine());
            left.add(v);
            if (left.size() > 1) {
                if (left.size() > right.size()) {
                    right.add(left.pollLast());
                }
                if (left.last() > right.first()) {
                    Integer r = right.pollFirst();
                    Integer l = left.pollLast();
                    left.add(r);
                    right.add(l);
                }
            }
            debug("%s %s%n", left, right);
            double median = median(left, right);
            debug("median: %.1f%n", median);
            answer("%.1f", out, median);
            if (i < n - 1) {
                answer("%n", out);
            }
        }
        out.flush();
    }

    private static double median(TreeSet<Integer> left, TreeSet<Integer> right) {
        if (left.size() == right.size()) {
            return ((double) left.last() + right.first()) / 2.0;
        }
        return left.size() > right.size() ? left.last() : right.first();
    }

    private static void debug(String format, Object... args) {
        if ("true".equals(System.getProperty("debug"))) {
            System.out.printf(format, args);
        }
    }

    private static void answer(String format, Writer w, Object... args) throws IOException {
        if (isNull(System.getProperty("debug"))) {
            w.write(format(format, args));
        }
    }
}
