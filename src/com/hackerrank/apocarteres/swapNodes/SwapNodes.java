package com.hackerrank.apocarteres.swapNodes;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;
import static java.lang.String.join;
import static java.util.stream.Collectors.toList;

//https://www.hackerrank.com/challenges/swap-nodes-algo/problem
public class SwapNodes {

    private static final Pattern NODE_ENTRY_PATTERN = Pattern.compile("(-?\\d+)\\s(-?\\d+)");

    public static void main(String[] args) throws IOException {
        new SwapNodes().solve(
                new BufferedReader(new InputStreamReader(System.in)),
                new BufferedWriter(new OutputStreamWriter(System.out))
        );
    }

    void solve(BufferedReader reader, BufferedWriter writer) throws IOException {
        Node tree = build(reader);
        int rotations = parseInt(reader.readLine());
        for (int i = 0; i < rotations; i++) {
            int swapIndex = parseInt(reader.readLine());
            swap(tree, 1, swapIndex);
            List<Integer> sink = new ArrayList<>();
            sink(tree, sink);
            print(sink, writer);
            if (i < rotations - 1) {
                writer.newLine();
            }
        }
        writer.close();
    }

    void print(List<Integer> sink, Writer writer) throws IOException {
        writer.write(join(" ", sink.stream().map(String::valueOf).collect(toList())));
        writer.flush();
    }

    Node build(BufferedReader reader) throws IOException {
        int length = parseInt(reader.readLine());
        List<Node[]> nodes = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            nodes.add(buildNode(reader));
        }
        Iterator<Node[]> it = nodes.iterator();
        Deque<Node> queue = new LinkedList<>();
        Node root = new Node(1);
        queue.add(root);
        while (it.hasNext()) {
            Node[] pair = it.next();
            Node node = queue.poll();
            node.left = pair[0];
            node.right = pair[1];
            if (!node.left.leaf()) {
                queue.add(node.left);
            }
            if (!node.right.leaf()) {
                queue.add(node.right);
            }
        }
        return root;
    }

    Node[] buildNode(BufferedReader reader) throws IOException {
        String input = reader.readLine();
        Matcher matcher = NODE_ENTRY_PATTERN.matcher(input);
        //noinspection ResultOfMethodCallIgnored
        matcher.find();
        Node left = new Node(parseInt(matcher.group(1)));
        Node right = new Node(parseInt(matcher.group(2)));
        return new Node[]{left, right};
    }

    void sink(Node root, List<Integer> out) {
        if (root.leaf()) {
            return;
        }
        sink(root.left, out);
        out.add(root.value);
        sink(root.right, out);
    }

    Node swap(Node root) {
        if (root.leaf()) {
            return root;
        }
        root.left = swap(root.left);
        root.right = swap(root.right);
        Node l = root.left;
        root.left = root.right;
        root.right = l;
        return root;
    }

    void swap(Node root, int index, int level) {
        if (root.leaf()) {
            return;
        }
        if (index % level == 0) {
            Node left = root.left;
            root.left = root.right;
            root.right = left;
        }
        swap(root.left, index + 1, level);
        swap(root.right, index + 1, level);
    }

}
