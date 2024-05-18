package com.hackerrank.apocarteres.binarysearch;

/**
 * Given an array of integers nums which is sorted in ascending order, and an integer target, write a function to search target in nums. If target exists, then return its index. Otherwise, return -1.
 * <p>
 * You must write an algorithm with O(log n) runtime complexity.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [-1,0,3,5,9,12], target = 9
 * Output: 4
 * Explanation: 9 exists in nums and its index is 4
 * Example 2:
 * <p>
 * Input: nums = [-1,0,3,5,9,12], target = 2
 * Output: -1
 * Explanation: 2 does not exist in nums so return -1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 104
 * -104 < nums[i], target < 104
 * All the integers in nums are unique.
 * nums is sorted in ascending order.
 */
class Solution {
  static void assertEq(int[] a, int v, int expected) {
    var solution = new Solution();
    var actual = solution.search(a, v);
    if (expected != actual) {
      throw new RuntimeException("invalid result. expected %d, but got %d".formatted(expected, actual));
    }
  }

  public static void main(String[] args) {
    assertEq(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 4, 3);
    assertEq(new int[]{}, 4, -1);
    assertEq(new int[]{5}, 4, -1);
    assertEq(new int[]{1, 2, 3}, 4, -1);
    assertEq(new int[]{4}, 4, 0);
    assertEq(new int[]{2, 5}, 5, 1);
    assertEq(new int[]{-1, 0, 3, 5, 9, 12}, 2, -1);
  }

  public int search(int[] nums, int target) {
    if (nums.length == 0) {
      return -1;
    }
    if (nums.length == 1) {
      return nums[0] == target ? 0 : -1;
    }
    return search(nums, 0, nums.length - 1, target);
  }

  public int search(int[] nums, int from, int to, int target) {
    if (from == to) {
      return nums[from] == target ? from : -1;
    }
    int m = (to - from) / 2;
    int r = from + m;
    if (r == nums.length) {
      return -1;
    }
    var c = nums[r];
    if (target == c) {
      return r;
    }
    if (target < c) {
      return search(nums, from, r, target);
    }
    return search(nums, r + 1, to, target);
  }
}