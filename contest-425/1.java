// https://leetcode.com/problems/minimum-positive-sum-subarray/submissions/1506816151/

import java.util.*;

class Solution {
  public int minimumSumSubarray(List<Integer> nums, int l, int r) {
      int minsum = Integer.MAX_VALUE;
      boolean found = false;
      for (int i = 0; i < nums.size(); i++) {
          int sum = 0;
           for (int j = i; j < nums.size() && (j - i + 1) <= r; j++) {
              sum += nums.get(j);
              if ((j - i + 1) >= l && sum > 0) {
                  minsum = Math.min(minsum, sum);
                  found = true;
              }
          }
      }
      return found ? minsum : -1;
  }
}