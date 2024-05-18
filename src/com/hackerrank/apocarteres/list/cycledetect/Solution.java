package com.hackerrank.apocarteres.list.cycledetect;

import com.hackerrank.apocarteres.common.ListNode;

/**
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
 * <p>
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
 * <p>
 * Return true if there is a cycle in the linked list. Otherwise, return false.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [3,2,0,-4], pos = 1
 * Output: true
 * Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).
 * Example 2:
 * <p>
 * <p>
 * Input: head = [1,2], pos = 0
 * Output: true
 * Explanation: There is a cycle in the linked list, where the tail connects to the 0th node.
 * Example 3:
 * <p>
 * <p>
 * Input: head = [1], pos = -1
 * Output: false
 * Explanation: There is no cycle in the linked list.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of the nodes in the list is in the range [0, 104].
 * -105 <= Node.val <= 105
 * pos is -1 or a valid index in the linked-list.
 */
public class Solution {
  public static void main(String[] args) {
    var solution = new Solution();
    ListNode l1 = new ListNode(300);
    ListNode l2 = new ListNode(200, l1);
    ListNode l3 = new ListNode(100, l2);
    var r = solution.hasCycle(l3);
    System.out.println(r);
  }

  public boolean hasCycle(ListNode head) {
    if (head == null || head.next == null) {
      return false;
    }
    var slow = head;
    var fast = head.next;
    while (fast != null) {
      slow = slow.next;
      fast = fast.next;
      if (fast == null) {
        break;
      }
      fast = fast.next;
      if (fast == slow) {
        return true;
      }
    }
    return false;
  }
}