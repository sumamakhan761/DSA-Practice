// https://leetcode.com/problems/maximum-number-of-distinct-elements-after-operations/description/

import java.util.*;

class Solution {
  public int maxDistinctElements(int[] nums, int k) {
      int last = Integer.MIN_VALUE;
      Arrays.sort(nums);
      Set<Integer> s = new HashSet<>();
      for(int num : nums){
          int x = Math.max(last+1, num-k);
          if(x <= num+k){  
              s.add(x);
              last = x;
          }
      }
      return s.size();
  }
}