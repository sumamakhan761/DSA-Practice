// https://leetcode.com/problems/maximum-difference-between-adjacent-elements-in-a-circular-array/submissions/1512646930/

class Solution {
    public int maxAdjacentDistance(int[] nums) {
        int maxDiff = 0;
        int n = nums.length;
        
        for (int i = 0; i < n; i++) {
            int diff = 0;
            if(i == n-1){
                diff =  Math.abs(nums[i] - nums[0]);
            }else{
             diff = Math.abs(nums[i] - nums[i + 1]);
            }
            maxDiff = Math.max(maxDiff, diff);
        }
        
        return maxDiff;
    }
}