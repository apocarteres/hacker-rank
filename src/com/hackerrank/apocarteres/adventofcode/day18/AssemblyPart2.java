package com.hackerrank.apocarteres.adventofcode.day18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;

public final class AssemblyPart2 {

    private static final Pattern PATTERN = Pattern.compile(
            "(?<command>[a-z]+)\\s(?<lvalue>(-?\\d+)|([a-z]))?\\s?(?<rvalue>(-?\\d+)|([a-z]))?"
    );
    private final Map<String, BiConsumer<ValueVariant, ValueVariant>> operations;
    private final Map<Character, Long> registers;
    private final BlockingQueue<Long> receiveBuffer;
    private int programId;
    private int stackOffset;
    private AtomicBoolean pending;
    private boolean terminated;
    private int sent;
    private AssemblyPart2 remote;

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        AssemblyPart2 one = new AssemblyPart2(0);
        AssemblyPart2 two = new AssemblyPart2(1);
        String[] input = loadInput();
        int result = runParallel(one, two, input, input);
        System.out.println(result);
    }

    AssemblyPart2(int programId) {
        this.pending = new AtomicBoolean();
        this.programId = programId;
        this.registers = new HashMap<>();
        this.operations = new HashMap<>();
        this.receiveBuffer = new LinkedBlockingQueue<>();
        this.registers.put('p', (long) programId);
        this.operations.put("snd", (v1, v2) -> send(v1));
        this.operations.put("set", (v1, v2) -> registers.put(v1.register, readValue(v2)));
        this.operations.put("add", (v1, v2) -> compute(v1.register, y -> y + readValue(v2)));
        this.operations.put("mul", (v1, v2) -> compute(v1.register, y -> y * readValue(v2)));
        this.operations.put("mod", (v1, v2) -> compute(v1.register, y -> y % readValue(v2)));
        this.operations.put("jgz", this::jump);
        this.operations.put("rcv", (v1, v2) -> receive(v1.register));
    }

    private int exec(String[] args, AssemblyPart2 remote) {
        this.remote = remote;
        for (int i = 0; i < args.length; i += stackOffset) {
            stackOffset = 1;
            eval(args[i]);
            if (terminated) {
                break;
            }
        }
        return sent;
    }

    private void receive(Character r) {
        Long value;
        for (; ; ) {
            try {
                value = receiveBuffer.poll(100, TimeUnit.MILLISECONDS);
                if (isNull(value)) {
                    if (pending.get() && remote.pending.get()) {
                        terminated = true;
                        return;
                    }
                    System.out.println(format("program %s pending elements...", programId));
                    pending.set(true);
                } else {
                    pending.set(false);
                    break;
                }
            } catch (InterruptedException ignored) {}
        }
        registers.put(r, value);
    }

    private void jump(ValueVariant v1, ValueVariant v2) {
        Long lvalue = readValue(v1);
        if (lvalue > 0) {
            stackOffset = readValue(v2).intValue();
        }
    }

    private void send(ValueVariant v) {
        Long value = readValue(v);
        try {
            remote.receiveBuffer.put(value);
            sent++;
        } catch (InterruptedException ignored) {}
    }

    private void eval(String s) {
        Matcher matcher = PATTERN.matcher(s);
        if (matcher.find()) {
            String cmd = matcher.group("command");
            BiConsumer<ValueVariant, ValueVariant> fn = operations.get(cmd);
            if (isNull(fn)) {
                throw new RuntimeException(format("no such command: %s", cmd));
            }
            ValueVariant lvalue = readVariant(matcher.group("lvalue"));
            ValueVariant rvalue = readVariant(matcher.group("rvalue"));
            if (isNull(lvalue) && isNull(rvalue)) {
                throw new IllegalStateException("no valid arguments");
            }
            fn.accept(lvalue, rvalue);
        } else {
            throw new IllegalStateException(format("can't eval instruction at %s", s));
        }
    }

    private ValueVariant readVariant(String s) {
        if (isNull(s)) {
            return null;
        }
        char c = s.charAt(0);
        if (Character.isAlphabetic(c)) {
            return new ValueVariant(c, null);
        }
        return new ValueVariant(null, Long.parseLong(s));
    }

    private Long compute(Character r, Function<Long, Long> v) {
        return registers.compute(r, (k, b) -> isNull(b) ? v.apply(0L) : v.apply(b));
    }

    private Long readValue(ValueVariant v) {
        if (v.isValue()) {
            return v.value;
        }
        return registers.getOrDefault(v.register, 0L);
    }

    private static String[] loadInput() {
        List<String> collect;
        try (InputStream is = AssemblyPart2.class.getResourceAsStream("assembly.txt")) {
            collect = new BufferedReader(new InputStreamReader(is)).lines().collect(toList());
        } catch (IOException e) {
            throw new RuntimeException("can't read file", e);
        }
        String[] result = new String[collect.size()];
        return collect.toArray(result);
    }

    static int runParallel(
            AssemblyPart2 one,
            AssemblyPart2 two,
            String[] input1,
            String[] input2
    ) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(() -> one.exec(input1, two));
        Integer result = service.submit(() -> two.exec(input2, one)).get();
        service.shutdown();
        return result;
    }

    private static final class ValueVariant {
        private final Character register;
        private final Long value;

        private ValueVariant(Character register, Long value) {
            this.register = register;
            this.value = value;
        }

        boolean isValue() {
            return nonNull(value);
        }
    }

}
