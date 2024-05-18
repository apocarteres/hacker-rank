package com.hackerrank.apocarteres.common;


import static java.lang.String.format;

public final class ListNode {
  public int val;
  public ListNode next;

  public ListNode() {
  }

  public ListNode(int val) {
    this.val = val;
  }

  public ListNode(int val, ListNode next) {
    this.val = val;
    this.next = next;
  }

  public void print() {
    System.out.print("list: ");
    var next = this;
    while (next != null) {
      System.out.print(next.val);
      next = next.next;
      if (next != null) {
        System.out.print(",");
      }
    }
    System.out.println();
  }

  @Override
  public String toString() {
    return format("%d", val) + (next == null ? "" : format("->%d", next.val));
  }

}

