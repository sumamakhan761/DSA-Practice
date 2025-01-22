// https://leetcode.com/problems/adjacent-increasing-subarrays-detection-ii/submissions/1516420027/

import java.util.*;
class Solution {
  public int maxIncreasingSubarrays(List<Integer> nums) {
      int prev = 0 ,curr = 1, res = 0;
      for(int i = 1 ; i < nums.size(); i++){
          if(nums.get(i-1) < nums.get(i)){ // check increasing
              curr++;
          }else{
              prev = curr;
              curr = 1;
          }

          res = Math.max(res , Math.max(curr/2 , Math.min(curr, prev))); // curr/2 isliye bcz may be like example i  found curr = 20 and prev = 6 so min is 6 but they want maximum so what i do 20 / 2 = 10 which safisfy condition also 
      }
  return res;
  }
}