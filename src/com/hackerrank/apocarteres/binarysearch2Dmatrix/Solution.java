package com.hackerrank.apocarteres.binarysearch2Dmatrix;

/**
 * You are given an m x n integer matrix matrix with the following two properties:
 * <p>
 * Each row is sorted in non-decreasing order.
 * The first integer of each row is greater than the last integer of the previous row.
 * Given an integer target, return true if target is in matrix or false otherwise.
 * <p>
 * You must write a solution in O(log(m * n)) time complexity.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * Output: true
 * Example 2:
 * <p>
 * <p>
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -104 <= matrix[i][j], target <= 104
 */
class Solution {
  public static void main(String[] args) {
    var matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    var solution = new Solution();
    System.out.println(solution.searchMatrix(matrix, 11));
  }

  private static int readValue(int[][] matrix, int ordinal) {
    var cols = matrix[0].length;
    var rows = matrix.length;
    var len = cols * rows;
    if (ordinal >= len) {
      return -1;
    }
    var row = ordinal / cols;
    var col = ordinal - row * cols;
    return matrix[row][col];
  }

  public boolean searchMatrix(int[][] matrix, int target) {
    var cols = matrix[0].length;
    var rows = matrix.length;
    
    var len = cols * rows;
    return search(matrix, len, 0, len, target) != -1;
  }

  public int search(int[][] nums, int len, int from, int to, int target) {
    if (from == to) {
      return readValue(nums, from) == target ? from : -1;
    }
    int m = (to - from) / 2;
    int r = from + m;
    if (r == len) {
      return -1;
    }
    var c = readValue(nums, r);
    if (target == c) {
      return r;
    }
    if (target < c) {
      return search(nums, len, from, r, target);
    }
    return search(nums, len, r + 1, to, target);
  }
}