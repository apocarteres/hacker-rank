package com.hackerrank.apocarteres.swapNodes;

import static java.util.Objects.isNull;

final class Node {
    final int value;
    Node left;
    Node right;

    private static final Node LEAF = new Node(-1);

    Node(int value) {
        this(value, LEAF, LEAF);
    }

    Node(int value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    Node with(Node left, Node right) {
        return new Node(value, left, right);
    }

    boolean leaf() {
        return value == -1;
    }

    @Override
    public String toString() {
        if (leaf()) {
            return "nil";
        }
        return "{" +
                "v:" + value +
                (isNull(left) ? "" : ", left=" + left) +
                (isNull(right) ? "" : ", right=" + right) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;

        Node node = (Node) o;

        return value == node.value;

    }

    @Override
    public int hashCode() {
        return value;
    }
}