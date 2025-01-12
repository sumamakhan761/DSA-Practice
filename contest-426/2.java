// https://leetcode.com/submissions/detail/1503627542/

import java.util.*;

class Solution {
  public int getLargestOutlier(int[] nums) {
      // Step 1: Count frequencies and find min/max
      Map<Integer, Integer> count = new HashMap<>();
      int min = Integer.MAX_VALUE;
      int max = Integer.MIN_VALUE;
      long totalSum = 0;
      
      for (int num : nums) {
          count.put(num, count.getOrDefault(num, 0) + 1);
          min = Math.min(min, num);
          max = Math.max(max, num);
          totalSum += num;
      }
      
      // Step 2: Check each unique number from max to min
      for (int num = max; num >= min; num--) {
          if (!count.containsKey(num)) continue;
          
          long remainingSum = totalSum - num;
          if (remainingSum % 2 == 0) {
              long halfSum = remainingSum / 2;
              if (count.containsKey((int)halfSum)) {
                  if (halfSum != num || count.get(num) > 1) {
                      return num;
                  }
              }
          }
      }
      
      return -1;
  }
}