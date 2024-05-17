package com.hackerrank.apocarteres.tree;

import static java.lang.String.format;

@SuppressWarnings("unused")
public final class IntNode {
  private static final int NIL = Integer.MIN_VALUE;
  private final int value;
  private IntNode left;
  private IntNode right;

  private static final IntNode LEAF = new IntNode(NIL);

  public IntNode(int value) {
    this(value, LEAF, LEAF);
  }

  public IntNode(int value, IntNode left, IntNode right) {
    this.value = value;
    this.left = left;
    this.right = right;
  }

  public IntNode with(IntNode left, IntNode right) {
    return new IntNode(value, left, right);
  }

  public void swap() {
    left = right;
  }

  public boolean leaf() {
    return value == NIL;
  }

  public IntNode left() {
    return left;
  }

  @Override
  public String toString() {
    if (leaf()) {
      return "nil";
    }
    return format("[%d]", value);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof IntNode)) return false;

    IntNode node = (IntNode) o;

    return value == node.value;

  }

  @Override
  public int hashCode() {
    return value;
  }

  public IntNode right() {
    return right;
  }

  public Integer value() {
    return value;
  }

  public void setLeft(IntNode node) {
    this.left = node;
  }

  public void setRight(IntNode node) {
    this.right = node;
  }
}