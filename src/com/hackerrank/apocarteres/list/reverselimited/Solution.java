package com.hackerrank.apocarteres.list.reverselimited;

import com.hackerrank.apocarteres.common.ListNode;

import java.util.ArrayList;

/**
 * Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position left to position right, and return the reversed list.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [1,2,3,4,5], left = 2, right = 4
 * Output: [1,4,3,2,5]
 * Example 2:
 * <p>
 * Input: head = [5], left = 1, right = 1
 * Output: [5]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is n.
 * 1 <= n <= 500
 * -500 <= Node.val <= 500
 * 1 <= left <= right <= n
 */

class Solution {
  public static void main(String[] args) {
    var solution = new Solution();
    solution.reverseBetween(new ListNode(1, new ListNode(2, new ListNode(3,
      new ListNode(4, new ListNode(5))))), 2, 4).print();
    solution.reverseBetween(new ListNode(3, new ListNode(5)), 1, 2).print();
    solution.reverseBetween(new ListNode(1, new ListNode(2, new ListNode(3,
      new ListNode(4, new ListNode(5))))), 3, 4).print();

  }

  public ListNode reverseBetween(ListNode head, int left, int right) {
    var nodes = new ArrayList<ListNode>();
    var node = head;
    int c = 0;
    while (node != null) {
      nodes.add(node);
      node = node.next;
      ++c;
    }
    if (c == 1) {
      return head;
    }
    if (left == right) {
      return head;
    }
    --left;
    --right;
    var reversed = (right + 1 < c) ? nodes.get(right + 1) : null;
    var view = nodes.subList(left, right + 1);
    for (ListNode o : view) {
      reversed = new ListNode(o.val, reversed);
    }
    if (left == 0) {
      return reversed;
    }
    node = head;
    int i = 0;
    while (i < c) {
      if (i == left-1) {
        node.next = reversed;
        break;
      }
      ++i;
      node = node.next;
    }
    return head;
  }

}