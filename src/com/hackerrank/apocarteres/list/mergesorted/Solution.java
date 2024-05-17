package com.hackerrank.apocarteres.list.mergesorted;

import java.time.Instant;


/**
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 * <p>
 * Merge all the linked-lists into one sorted linked-list and return it.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * Explanation: The linked-lists are:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * merging them into one sorted list:
 * 1->1->2->3->4->4->5->6
 * Example 2:
 * <p>
 * Input: lists = []
 * Output: []
 * Example 3:
 * <p>
 * Input: lists = [[]]
 * Output: []
 * <p>
 * <p>
 * Constraints:
 * <p>
 * k == lists.length
 * 0 <= k <= 104
 * 0 <= lists[i].length <= 500
 * -104 <= lists[i][j] <= 104
 * lists[i] is sorted in ascending order.
 * The sum of lists[i].length will not exceed 104.
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

class ListNode {
  int val;
  ListNode next;

  ListNode() {
  }

  ListNode(int val) {
    this.val = val;
  }

  ListNode(int val, ListNode next) {
    this.val = val;
    this.next = next;
  }
}

class Solution {
  static void printList(ListNode node) {
    System.out.print("list: ");
    var next = node;
    while (next != null) {
      System.out.print(next.val);
      next = next.next;
    }
    System.out.println();
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    ListNode[] nodes = {
      new ListNode(1, new ListNode(4, new ListNode(5))),
      new ListNode(1, new ListNode(3, new ListNode(4))),
      new ListNode(2, new ListNode(6))
    };
    ListNode node;
    long t = Instant.now().getNano();
    node = solution.mergeKLists(nodes);
    t = Instant.now().getNano() - t;
    System.out.println("test1");
    printList(node);
    System.out.println("time: " + t);
    System.out.println("test2");
    printList(solution.mergeKLists(new ListNode[]{
      new ListNode(),
      new ListNode(1),
    }));
    System.out.println("test3");
    printList(solution.mergeKLists(new ListNode[]{
      new ListNode(5),
      new ListNode(),
    }));
  }

  public ListNode mergeKLists(ListNode[] nodes) {
    // [1],4,5 | [1],3,4 | [2],6 -> 1,1,2
    // 1,[4],5 | 1,[3],4 | 2,[6] -> 3
    // 1,[4],5 | 1,3,[4] | 2,[6] -> 4,4
    // 1,4,[5] | 2,[6] -> 5
    // 2,[6] -> 6
    // result: 1,1,2,3,4,4,5,6
    ListNode parent = null;
    ListNode child = null;
    var nodes_length = nodes.length;
    while (true) {
      ListNode min = null;
      for (var i = 0; i < nodes_length; ++i) {
        var node = nodes[i];
        if (node == null) {
          continue;
        }
        if (min == null || node.val < min.val) {
          min = node;
        }
      }
      if (min == null) {
        break;
      }
      for (var i = 0; i < nodes_length; ++i) {
        var node = nodes[i];
        if (node == null) {
          continue;
        }
        if (node.val == min.val) {
          if (child != null) {
            child.next = node;
            child = child.next;
          } else if (parent == null) {
            parent = node;
          } else {
            parent.next = node;
            child = parent.next;
          }
          //
          nodes[i] = node.next;
          if (nodes[i] == null) {
            nodes[i] = nodes[nodes_length - 1];
            --nodes_length;
          }
        }
      }
    }
    return parent;
  }
}