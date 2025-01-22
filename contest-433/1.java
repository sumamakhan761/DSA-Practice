// https://leetcode.com/submissions/detail/1514113446/

class Solution {
  public int subarraySum(int[] nums) {
      int sum = 0 ; 
      int start = 0;
       for(int i = 0 ; i < nums.length; i++){
           start = Math.max(0 , i-nums[i]);
           for(int j = start; j<=i; j++){
               sum += nums[j];
           }
       }
      return sum;
  }
}