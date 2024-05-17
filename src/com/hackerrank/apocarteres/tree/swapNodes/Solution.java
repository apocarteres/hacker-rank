package com.hackerrank.apocarteres.tree.swapNodes;

import com.hackerrank.apocarteres.tree.IntNode;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;
import static java.lang.String.join;
import static java.util.Collections.emptyList;
import static java.util.regex.Pattern.compile;
import static java.util.stream.Collectors.toList;

//https://www.hackerrank.com/challenges/swap-nodes-algo/problem
public class Solution {

    private static final Pattern NODE_ENTRY_PATTERN = compile("(-?\\d+)\\s(-?\\d+)");

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
                new Solution().solve(reader, writer);
            }
        }
    }

    public void solve(BufferedReader reader, BufferedWriter writer) throws IOException {
        IntNode tree = build(reader);
        int rotations = parseInt(reader.readLine());
        for (int i = 0; i < rotations; i++) {
            int swapIndex = parseInt(reader.readLine());
            swap(tree, 1, swapIndex);
            print(sink(tree), writer);
            if (i < rotations - 1) {
                writer.newLine();
            }
        }
    }

    public void print(List<Integer> sink, Writer writer) throws IOException {
        writer.write(join(" ", sink.stream().map(String::valueOf).collect(toList())));
    }

    public IntNode build(BufferedReader reader) throws IOException {
        int length = parseInt(reader.readLine());
        List<IntNode[]> nodes = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            nodes.add(buildNode(reader));
        }
        Iterator<IntNode[]> it = nodes.iterator();
        Deque<IntNode> queue = new LinkedList<>();
        IntNode root = new IntNode(1);
        queue.add(root);
        while (it.hasNext()) {
            IntNode[] pair = it.next();
//            Node node = queue.poll();
            IntNode left = pair[0];
            IntNode right = pair[1];
            if (!left.leaf()) {
                queue.add(left);
            }
            if (!right.leaf()) {
                queue.add(right);
            }
        }
        return root;
    }

    IntNode[] buildNode(BufferedReader reader) throws IOException {
        String input = reader.readLine();
        Matcher matcher = NODE_ENTRY_PATTERN.matcher(input);
        //noinspection ResultOfMethodCallIgnored
        matcher.find();
        IntNode left = new IntNode(parseInt(matcher.group(1)));
        IntNode right = new IntNode(parseInt(matcher.group(2)));
        return new IntNode[]{left, right};
    }

    public List<Integer> sink(IntNode root) {
        if (root.leaf()) {
            return emptyList();
        }
        List<Integer> result = new ArrayList<>();
        result.addAll(sink(root.left()));
        result.add(root.value());
        result.addAll(sink(root.right()));
        return result;
    }

    public void swap(IntNode root, int index, int level) {
        if (root.leaf()) {
            return;
        }
        if (index % level == 0) {
            IntNode left = root.left();
            root.setLeft(root.right());
            root.setRight(left);
        }
        swap(root.left(), index + 1, level);
        swap(root.right(), index + 1, level);
    }

}
