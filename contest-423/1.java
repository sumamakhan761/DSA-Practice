
// https://leetcode.com/problems/adjacent-increasing-subarrays-detection-i/submissions/1516405706/

import java.util.*;
class Solution {
  public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
      for(int i = 0 ; i <= nums.size() - 2 * k; i++){
         if(isincreasing(i, k, nums) && isincreasing(i+k , k, nums)){
             return true;
         };
      }
      return false;
  }

  private boolean isincreasing(int start, int end, List<Integer>nums){
      for(int i = start ; i < end  + start - 1; i++){
          if(nums.get(i) >= nums.get(i+1)){
              return false;
          }
      }
      return true;
  }
}