package com.hackerrank.apocarteres.adventofcode.day18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;
import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

public final class Assembly {

    private static final Pattern PATTERN = Pattern.compile(
            "(?<command>[a-z]+)\\s(?<register>[a-z])\\s?(?<i32value>-?\\d+)?(?<regvalue>[a-z])?");
    private final Map<String, BiConsumer<Character, Long>> operations;
    private final Map<Character, Long> registers;
    private final Map<Character, Long> sounds;
    private int stackOffset;
    private int recovered;

    public static void main(String[] args) throws IOException {
        System.out.println(new Assembly().exec(loadInput()));
    }

    public Assembly() {
        this.registers = new HashMap<>();
        this.operations = new HashMap<>();
        this.sounds = new HashMap<>();
        this.operations.put("snd", (r, v) -> send(r));
        this.operations.put("set", registers::put);
        this.operations.put("add", (r, v) -> compute(r, y -> y + v));
        this.operations.put("mul", (r, v) -> compute(r, y -> y * v));
        this.operations.put("mod", (r, v) -> compute(r, y -> y % v));
        this.operations.put("jgz", this::jump);
        this.operations.put("rcv", (r, v) -> recover(r));
    }

    public int exec(String[] args) {
        for (int i = 0; i < args.length; i += stackOffset) {
            stackOffset = 1;
            eval(args[i]);
            if (recovered != 0) {
                break;
            }
        }
        return recovered;
    }

    private void recover(Character r) {
        Long recovered = sounds.getOrDefault(r, 0L);
        if (recovered != 0) {
            this.recovered = recovered.intValue();
        }
    }

    private void jump(char r, Long v) {
        if (registers.getOrDefault(r, 0L) > 0) {
            stackOffset = v.intValue();
        }
    }

    private void send(Character r) {
        Long freq = registers.getOrDefault(r, 0L);
        if (freq != 0) {
            sounds.put(r, freq);
        }
    }

    private void eval(String s) {
        Matcher matcher = PATTERN.matcher(s);
        if (matcher.find()) {
            String cmd = matcher.group("command");
            BiConsumer<Character, Long> fn = operations.get(cmd);
            if (isNull(fn)) {
                throw new RuntimeException(format("no such command: %s", cmd));
            }
            char register = matcher.group("register").charAt(0);
            Long value = ofNullable(matcher.group("i32value")).map(Long::parseLong).orElse(null);
            if (isNull(value)) {
                value = ofNullable(matcher.group("regvalue"))
                        .map(x -> x.charAt(0))
                        .map(registers::get)
                        .orElse(null);
            }
            fn.accept(register, value);
        } else {
            throw new IllegalStateException(format("can't eval instruction at %s", s));
        }
    }

    private Long compute(Character r, Function<Long, Long> v) {
        return registers.compute(r, (k, b) -> isNull(b) ? v.apply(0L) : v.apply(b));
    }

    private static String[] loadInput() {
        List<String> collect;
        try (InputStream is = Assembly.class.getResourceAsStream("assembly.txt")) {
            collect = new BufferedReader(new InputStreamReader(is)).lines().collect(toList());
        } catch (IOException e) {
            throw new RuntimeException("can't read file", e);
        }
        String[] result = new String[collect.size()];
        return collect.toArray(result);
    }
}
