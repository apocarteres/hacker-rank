package com.hackerrank.apocarteres.list.mergesorted;

import com.hackerrank.apocarteres.common.ListNode;

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

class Solution {
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
    node.print();
    System.out.println("time: " + t);
    System.out.println("test2");
    solution.mergeKLists(new ListNode[]{
      new ListNode(),
      new ListNode(1),
    }).print();
    System.out.println("test3");
    solution.mergeKLists(new ListNode[]{
      new ListNode(5),
      new ListNode(),
    }).print();
    System.out.println("test4");
    node = null;
    for (int i=0; i< 10000; ++i) {
      node = new ListNode(i, node);
    }
    nodes = new ListNode[]{
      node,
      new ListNode(3000),
      new ListNode(5000),
    };
    t = Instant.now().getNano();
    node = solution.mergeKLists(nodes);
    t = Instant.now().getNano() - t;
//    printList(node);
    System.out.println("time: " + t);

  }

  public ListNode mergeKLists(ListNode[] nodes) {
    // [1],4,5 | [1],3,4 | [2],6 -> 1,1,2
    // 1,[4],5 | 1,[3],4 | 2,[6] -> 3
    // 1,[4],5 | 1,3,[4] | 2,[6] -> 4,4
    // 1,4,[5] | 2,[6] -> 5
    // 2,[6] -> 6
    // result: 1,1,2,3,4,4,5,6
    ListNode parent = null; // to keep root ref, otherwise memory will be released (c++ winks here)
    ListNode child = null;
    var length = nodes.length;
    while (true) {
      ListNode min = null;
      for (var i = 0; i < length; ++i) {
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
      for (var i = 0; i < length; ++i) {
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
            nodes[i] = nodes[length - 1];
            --length;
          }
        }
      }
    }
    return parent;
  }
}