package com.hackerrank.apocarteres.bigintsum;

import com.hackerrank.apocarteres.common.ListNode;

import java.time.Instant;

/**
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * <p>
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807.
 * Example 2:
 * <p>
 * Input: l1 = [0], l2 = [0]
 * Output: [0]
 * Example 3:
 * <p>
 * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * Output: [8,9,9,9,0,0,0,1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in each linked list is in the range [1, 100].
 * 0 <= Node.val <= 9
 * It is guaranteed that the list represents a number that does not have leading zeros.
 */
class Solution {
  public static void main(String[] args) {
    var l1 = new ListNode(8, new ListNode(3));
    var l2 = new ListNode(9, new ListNode(5, new ListNode(1)));
    var solution = new Solution();
    long t = Instant.now().getNano();
    var r = solution.addTwoNumbers(l1, l2);
    t = Instant.now().getNano() - t;
    r.print();
    System.out.println("time: " + t);
  }

  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    byte[] b1 = new byte[100];
    byte[] b2 = new byte[100];
    byte[] b3 = new byte[100];
    var next_l1 = l1;
    var next_l2 = l2;
    int i = 0;
    while (next_l1 != null) {
      b1[i] = (byte) (next_l1.val);
      next_l1 = next_l1.next;
      if (next_l2 != null) {
        b2[i] = (byte) (next_l2.val);
        next_l2 = next_l2.next;
      }
      ++i;
    }
    int l1s = i;
    while (next_l2 != null) {
      b2[i] = (byte) (next_l2.val);
      next_l2 = next_l2.next;
      ++i;
    }
    int l2s = i;
    int r = 0;
    int max_len = Math.min(100, 1 + Math.max(l1s, l2s));
    for (i = 0; i < max_len; ++i) {
      int a = i < l1s ? b1[i] : 0;
      int b = i < l2s ? b2[i] : 0;
      int t = a + b + r;
      if (t > 9) {
        b3[i] = (byte) (t - 0xA);
        r = 1;
      } else {
        b3[i] = (byte) (t);
        r = 0;
      }
    }
    if (b3[max_len - 1] == 0) {
      --max_len;
    }
    ListNode result = null;
    for (i = max_len - 1; i >= 0; --i) {
      result = new ListNode(b3[i], result);
    }
    return result;
  }

}
