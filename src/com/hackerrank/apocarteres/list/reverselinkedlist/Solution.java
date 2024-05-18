package com.hackerrank.apocarteres.list.reverselinkedlist;

import com.hackerrank.apocarteres.common.ListNode;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

class Solution {

    public static void main(String[] args) {
        var l1 = new ListNode(5, new ListNode(3, new ListNode(2)));
        var result = new Solution().reverseList(l1);
        result.print();
    }

    public ListNode reverseList(ListNode head) {
        var nodes = new ArrayList<Integer>();
        var node = head;
        while (node != null) {
            nodes.add(node.val);
            node = node.next;
        }
        var result = (ListNode) null;
        for (int o : nodes) {
            result = new ListNode(o, result);
        }
        return result;
    }
}