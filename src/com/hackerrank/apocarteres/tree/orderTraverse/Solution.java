package com.hackerrank.apocarteres.tree.orderTraverse;

import com.hackerrank.apocarteres.tree.IntNode;

import java.util.Scanner;

//https://www.hackerrank.com/challenges/tree-level-order-traversal/problem
public final class Solution {

  public static void levelOrder(IntNode root) {
    System.out.println(root.value());
    if (!root.left().leaf()) {
      System.out.println(root.left().value());
    }
    if (!root.right().leaf()) {
      System.out.println(root.right().value());
    }
  }

  public static IntNode insert(IntNode root, int data) {
    if (root == null) {
      return new IntNode(data);
    } else {
      if (data <= root.value()) {
        root.setLeft(new IntNode(data));
      } else {
        root.setRight(new IntNode(data));
      }
    }
    return null;
  }

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    int t = scan.nextInt();
    IntNode root = null;
    while (t-- > 0) {
      int data = scan.nextInt();
      root = insert(root, data);
    }
    scan.close();
    levelOrder(root);
  }
}
